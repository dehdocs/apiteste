node('docker-node') {
  def name_img = "${env.JOB_NAME}".split('/').last().toLowerCase()
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
    
  }

}