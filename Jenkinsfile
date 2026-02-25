pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/2440671/backend.git'
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker-compose build'
            }
        }

        stage('Run Services') {
            steps {
                sh 'docker-compose up -d'
            }
        }

        stage('Smoke Test') {
            steps {
                sh 'curl -f http://localhost:8081/actuator/health || exit 1'
            }
        }
    }

    post {
        always {
            sh 'docker-compose down'
        }
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
