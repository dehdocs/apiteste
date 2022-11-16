node('docker-node') {
  def name_img = "msteste:1.0.1"
  stage('Prepare') {
    checkout scm
  }
  stage('build'){
    docker.withRegistry('http://20.231.125.187:8182', 'nexus') {
      def dockerImage = docker.build("$name_img")
      dockerImage.push()
    }
  }
  stage('deploy'){
    withCredentials([file(credentialsId: '	kubeconfig', variable: 'SECRET_FILE')]) {
      sh 'KUBECONFIG=$SECRET_FILE kubectl apply -f deployment.yaml'
    }
  }

}