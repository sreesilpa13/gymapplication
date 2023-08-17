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
        stage("Build Modules") {
            steps {
                script {
                    def modules = ['gymservice', 'gymnotificationservice']
                    for (def module in modules) {
                        dir("${module}") {
                            echo "Hi came to this............................"
                            bat "mvn clean install"
                        }
                    }
                }
            }
        }
        stage("Build Docker Image") {
            steps {
                script {
                    def modules = ['gymservice', 'gymnotificationservice']
                    for (def module in modules) {
                        dir("${module}") {
                            dir("target") {
                                echo "Came Last"
                                bat "docker build -t ${module}:latest -f ../../Dockerfile ."
                            }
                        }
                    }
                }
            }
        }
    }
}
