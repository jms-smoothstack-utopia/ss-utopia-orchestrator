pipeline {
    agent any
    stages {
        stage('Clean package build') {
            steps {
                sh 'mvn clean package docker:build'
            }
        }
        stage('Push image to repository') {
            when {
                anyOf{
                    branch 'main'
                    branch 'dev'
                }
            }
            steps {
                sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 247293358719.dkr.ecr.us-east-1.amazonaws.com'
                sh 'mvn git-commit-id:revision docker:push'
            }
        }
    }
    post {
        always {
            sh 'mvn clean'
            sh 'docker system prune -f'
        }
    }
}