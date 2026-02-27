pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {
        stage('Checkout') {
            steps {
                    git branch: 'master', url: 'https://github.com/2440671/Backend.git'
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Run Services') {
            steps {
                sh 'docker compose up -d'
            }
        }

        stage('Smoke Test') {
    steps {
        sh 'sleep 20'
        sh 'curl -f http://api-gateway:8081/actuator/health'
    }
}
    }

    post {
        always {
            sh 'docker compose down'
        }
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
