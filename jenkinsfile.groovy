node('docker-node') {
  def name_img = "msteste:1.0.1"
  stage('Prepare') {
    checkout scm
  }
  stage('build'){
    docker.withRegistry('https://registry.devops.7f8254f4188647b4be19.eastus.aksapp.io', 'nexus') {
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