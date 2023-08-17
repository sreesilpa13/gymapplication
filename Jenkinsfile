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
                    def parentPomContent = readFile('pom.xml')
                    def modules = extractModulesFromParentPom(parentPomContent)
                    for (def module in modules) {
                        dir("${module}") {
                            echo "Building ${module}... Hey Good"
                            bat "mvn clean install"
                        }
                    }
                }
            }
        }
    }
}

def extractModulesFromParentPom(pomContent) {
    def pom = new XmlSlurper().parseText(pomContent)
    return pom.modules.module.collect { it.text() }
}
