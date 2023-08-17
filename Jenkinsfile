pipeline{
    agent any
    tools{
        maven "MavenTool"
    }
    stages{
        stage("Checkout"){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sreesilpa13/gymapplication']])
            }
        }
        stage("Build Modules"){
            steps{
                script {
                    def modules = ['gymservice', 'gymnotificationservice']
                    for (def module in modules) {
                        dir("${module}") {
                            // Use 'mvn clean install' to build the Spring Boot project
                            sh "mvn clean install"
                        }
                    }
                }
            }
        }
        stage("Build Docker Image"){
            steps{
                script{
                    def modules = ['gymservice', 'gymnotificationservice']
                    for (def module in modules) {
                        dir("${module}/target") {
                            docker.build("${module}:latest", "-f ../../Dockerfile .")
                        }
                    }
                }
            }
        }
    }
}