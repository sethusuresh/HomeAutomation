pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
            	dir("/var/lib/jenkins/workspace/Home_Automation"){
	                sh "chmod 777 /var/lib/jenkins/workspace/Home_Automation"
	                echo "Gradle build started"
	                sh "gradle build"
	                echo "Gradle build completed"
            	}
            }
        }
        stage('Deploy') {
            steps {
            	dir("/var/lib/jenkins/workspace/Home_Automation/build/libs"){
            	    sh "cp homeAutomation.jar /jar"
	                echo "Starting java application deployment"
	                sh "java -jar homeAutomation.jar"
	                echo "Java application deployment completed"
            	}
            }
        }
        stage('Clean Workspace') {
            steps {
            	dir("/var/lib/jenkins/workspace"){
	                sh "rm -r Home_Automation"
	                echo "Jenkins workspace cleaned"
            	}
            }
        }
    }
}
