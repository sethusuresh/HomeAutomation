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
	            	fileOperations([fileCopyOperation(excludes: '', flattenFiles: false, includes: 'homeAutomation.jar', renameFiles: false, sourceCaptureExpression: '', targetLocation: '/jar/home_automation', targetNameExpression: '')])
            	}
            	dir("/jar/home_automation"){
            		script{
            		    echo "Starting java application deployment"
	                	sh "java -jar homeAutomation.jar"
	                	echo "Java application deployment completed"
            		}
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
