pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh cd /var/lib/jenkins/workspace/Home_Automation
                sh sudo chmod 777 /var/lib/jenkins/workspace/Home_Automation
                echo "Gradle build started"
                sh gradle build
                echo "Gradle build completed"
                sh cd /var/lib/jenkins/workspace/Home_Automation/build/libs
            }
        }
        stage('Deploy') {
            steps {
                sh sudo cp homeAutomation.jar /jar
                echo "Starting java application deployment"
                sh java -jar homeAutomation.jar
                echo "Java application deployment completed"
            }
        }
        stage('Clean Workspace') {
            steps {
                sh cd /var/lib/jenkins/workspace
                sh sudo rm -r Home_Automation
                echo "Jenkins workspace cleaned"
            }
        }
    }
}
