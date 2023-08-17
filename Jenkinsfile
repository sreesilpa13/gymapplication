pipeline {
    agent any

    environment {
        PATH = "$PATH:/usr/local/bin"  // Add Docker path to PATH
    }

    tools {
        maven "MavenTool"
    }

    stages {
        stage("Checkout") {
            steps {
                checkout scmGit(branches: [[name: '*/main']], browser: github('https://github.com/sreesilpa13/gymapplication'), extensions: [], userRemoteConfigs: [[url: 'https://github.com/sreesilpa13/gymapplication.git']])
            }
        }
        stage("Build Modules & Build Docker Images") {
            steps {
                script {
                    def modules = ['gymservice', 'gymnotificationservice']
                    for (def module in modules) {
                        dir("${module}") {
                            echo "Building ${module}..."
                            bat "mvn clean install"
                        }
                    }
                }
            }
        }
        stage("Push Docker Images to Docker Hub") {
            steps {
                script {
                    def dockerHubUsername = 'danvi'
                    def dockerHubPassword = 'DanviShanmuki@2'

                    def modules = ['gymservice', 'gymnotificationservice']
                    for (def module in modules) {
                        def version = currentBuild.displayName
                        def imageName = "${dockerHubUsername}/${module}"

                        echo "Pushing Docker image: ${imageName}"
                        bat "docker login -u ${dockerHubUsername} -p ${dockerHubPassword}"
                        bat "docker push ${imageName}"
                    }
                }
            }
        }
    }
}
