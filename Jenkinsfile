def remote = [:]
remote.name = "raspberrypi"
remote.host = "ssautohome.hopto.org"
remote.allowAnyHosts = true
remote.user = "jenkins"
remote.password = "SS1994ekm@"

pipeline {
    agent any
    stages {
		stage('Test') {
            steps {
			    script{
				    echo "Hellow world test    "
				    echo "the workspace is:- ${WORKSPACE}"
			    }
            }
        } 
        stage('Build') {
            steps {
            	dir("${WORKSPACE}"){
	                echo "Gradle build started"
	                sh "gradle build -x test"
	                echo "Gradle build completed"
            	}
            }
        }
        stage('testting'){
        	steps{
				    withCredentials([usernamePassword(credentialsId: 'Rpi-ssh-cred', passwordVariable: '', usernameVariable: '')]) {
				            sshCommand remote: remote, command: 'sudo ls -lrt'
				        /*stage("SSH Steps Rocks!") {
				            //writeFile file: 'test.sh', text: 'ls'
				            //sshScript remote: remote, script: 'test.sh'
				            //sshPut remote: remote, from: 'test.sh', into: '.'
				            //sshGet remote: remote, from: 'test.sh', into: 'test_new.sh', override: true
				            //sshRemove remote: remote, path: 'test.sh'
				        }*/
				    }
	            //node {
				//}
        	}
        }
        /*stage('Copying JAR') {
            steps {
            	dir("/var/lib/jenkins/workspace/Home_Automation/build/libs"){
	            	fileOperations([fileCopyOperation(excludes: '', flattenFiles: false, includes: 'homeAutomation.jar', renameFiles: false, sourceCaptureExpression: '', targetLocation: '/jar/home_automation', targetNameExpression: '')])
            	}
            }
        }
        stage('Deploy') {
            steps {
            	dir("/jar/home_automation"){
            		script{
            			echo "kill application running in port 9090"
            			sh 'kill -9 $(lsof -t -i:9090) || true'
        				echo "Starting java application deployment"
                		sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar homeAutomation.jar &'
                		echo "Java application deployment completed"
            		}
            	}
            }
        }
        stage('Workspace cleanup') {
            steps {
            	cleanWs notFailBuild: true
            	dir("/var/lib/jenkins/workspace/Home_Automation@tmp"){
            	    deleteDir()
            	}
            	dir("/jar/home_automation@tmp"){
            	    deleteDir()
            	}
            }
        } */
    }
}







