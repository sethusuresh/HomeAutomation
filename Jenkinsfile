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
            			//echo "kill application running in port 9090"
            			//sh '''
            				//fuser -k 9090/tcp
        				//'''
            			//withEnv(['JENKINS_NODE_COOKIE=DontKillMe']) {
            				echo "Starting java application deployment"
	                		//sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar homeAutomation.jar &'
	                		sh 'java -jar homeAutomation.jar'
	                		echo "checking if the process has started:- "
	                		//sh 'lsof -t -i:9090'
	                		echo "Java application deployment completed"
            			//}
            		}
            	}
            }
        }
    }
}
