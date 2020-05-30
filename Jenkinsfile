def remote = [:]
remote.name = "raspberrypi"
remote.host = "ssautohome.hopto.org"
remote.allowAnyHosts = true
def value = ""
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
            	dir("${WORKSPACE}"){
	                echo "Gradle build started"
	                sh "gradle build -x test"
	                echo "Gradle build completed"
            	}
            }
        }
        stage('Copying JAR To RaspberryPi Server') {
            steps {
            	echo "Copying JAR started"
            	withCredentials([usernamePassword(credentialsId: 'Rpi-ssh-cred', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
            		script{
	            		remote.user = "$USERNAME"
						remote.password = "$PASSWORD"
            		}
		            sshPut remote: remote, from: 'build/libs/homeAutomation.jar', into: './jar/'
			    }
			    echo "Copying JAR completed"
            }
        }
        stage('Deploy'){
        	steps{
			    withCredentials([usernamePassword(credentialsId: 'Rpi-ssh-cred', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
			    		script{
			    		    remote.user = "$USERNAME"
							remote.password = "$PASSWORD"
			    		}
			            sshPut remote: remote, from: 'Dockerfile', into: '.'
			            sshCommand remote: remote, command: 'docker build /home/jenkins -t home_automation'
			            script{
				            //sshCommand remote: remote, command: 'docker rmi $(docker images -qa -f "dangling=true")'
				            value = sshCommand remote: remote, command: 'docker images -qa -f "dangling=true"'
				            echo "value"
				            echo value
			            }
			            sshCommand remote: remote, command: 'docker stop home_automation'
			            sshCommand remote: remote, command: 'docker rm home_automation'
			            sshCommand remote: remote, command: 'docker run --name home_automation -p 9090:9090 -d home_automation'
			    }
        	}
        }
        /*
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







