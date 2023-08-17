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
                    def moduleNames = findModuleNames()
                    for (def moduleName in moduleNames) {
                        dir("${moduleName}") {
                            echo "Building ${moduleName}..."
                            bat "mvn clean install"
                        }
                    }
                }
            }
        }
    }
}

def findModuleNames() {
    def moduleNames = []
    def pomFiles = findFiles(glob: '**/pom.xml')

    pomFiles.each { pomFile ->
        def pomContent = readFile(pomFile.toString())
        def matcher = (pomContent =~ /<artifactId>(.*?)<\/artifactId>/)

        if (matcher.find()) {
            moduleNames.add(matcher.group(1))
        }
    }

    return moduleNames
}
