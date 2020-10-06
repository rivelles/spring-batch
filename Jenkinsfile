pipeline {
    agent {
        docker {
            image 'maven:3.6-jdk-11'
            args '-v /root/.m2:/var/maven/.m2'
        }
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn test'
            }
        }
    }
}

