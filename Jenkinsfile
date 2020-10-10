pipeline {
    agent {
        docker {
            image 'maven:3.6-jdk-11'
            args '-v /tmp/maven:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2'
        }
    }
    stages {
        stage('test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('approve') {
            steps {
                script {
                    env.APPROVED_DEPLOY = input message: 'User input required',
                    parameters: [choice(name: 'Deploy?', choices: 'no\nyes', description: 'Choose "yes" if you want to deploy this build')]
                }
            }
        }

        stage('abort deploy') {
            when{
                environment name:'APPROVED_DEPLOY',value:'no'
            }
            steps {
                echo 'Aborting'
                post {
                    always {
                        cleanWS()
                    }
                }
            }
        }

        stage('build image') {
            when {
                environment name:'APPROVED_DEPLOY', value: 'yes'
            }
            steps {
                echo 'Will build image'
            }
        }
    }
}

