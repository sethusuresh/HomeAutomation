def remote = [:]
remote.name = "raspberrypi"
remote.host = "ssautohome.hopto.org"
remote.allowAnyHosts = true
def oldImageId = ""
def dockerUsername = ""
def dockerPassword = ""
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
            	echo "*****************Build*****************"
            	dir("${WORKSPACE}"){
	                echo "Gradle build started"
	                sh "gradle build -x test"
	                echo "Gradle build completed"
            	}
            }
        }
        stage('Setting Credentials') {
            steps {
            	echo "**********Fetching Credentials**********"
            	withCredentials([usernamePassword(credentialsId: 'Rpi-ssh-cred', passwordVariable: 'RPI_PASSWORD', usernameVariable: 'RPI_USERNAME')]) {
            		script{
	            		remote.user = "$USERNAME"
						remote.password = "$PASSWORD"
            		}
			    }
			    echo "Setting Rpi SSH credentials completed"
			    withCredentials([usernamePassword(credentialsId: 'Docker-cred', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
            		script{
	            		dockerUsername = "$DOCKER_USERNAME"
						dockerPassword = "$DOCKER_PASSWORD"
            		}
			    }
            }
        }
        stage('Copying JAR To Server') {
            steps {
            	echo "**********Copying JAR To Server**********"
            	echo "Copying JAR started"
            	/*withCredentials([usernamePassword(credentialsId: 'Rpi-ssh-cred', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
            		script{
	            		remote.user = "$USERNAME"
						remote.password = "$PASSWORD"
            		}
			    }*/
	            sshPut remote: remote, from: 'build/libs/homeAutomation.jar', into: './jar/'
			    echo "Copying JAR completed"
            }
        }
        stage('Docker Image Creation'){
        	steps{
        		echo "**********Docker Image Creation**********"
			    withCredentials([usernamePassword(credentialsId: 'Rpi-ssh-cred', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
		    		script{
		    		    remote.user = "$USERNAME"
						remote.password = "$PASSWORD"
		    		}
		    		echo "Copying Dockerfile"
		            sshPut remote: remote, from: 'Dockerfile', into: '.'
		            echo "Building docker image"
		            sshCommand remote: remote, command: 'docker build /home/jenkins -t sethusuresh/home_automation'
		            script{
			            oldImageId = sshCommand remote: remote, command: 'docker images -qa -f "dangling=true" || true'
			            if(oldImageId != null && !oldImageId.trim().isEmpty()){
			            	echo "Removing the old docker image:- ${oldImageId}"
				            sshCommand remote: remote, command: "docker rmi ${oldImageId}"
                      	}
		            }
		            withCredentials([usernamePassword(credentialsId: 'Docker-cred', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
		            	echo "Logging into DockerHub"
			            sshCommand remote: remote, command: "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
			            echo "Pushing latest image to DockerHub"
			            sshCommand remote: remote, command: 'docker push sethusuresh/home_automation'
			            echo "Logging out from DockerHub"
			            sshCommand remote: remote, command: 'docker logout'
		            }
			    }
        	}
        }
        stage('Deploy') {
            steps {
            	echo "**********Deploy**********"
            	withCredentials([usernamePassword(credentialsId: 'Rpi-ssh-cred', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
            		script{
	            		remote.user = "$USERNAME"
						remote.password = "$PASSWORD"
            		}
		            echo "Stopping the old container"
		            sshCommand remote: remote, command: 'docker stop home_automation || true'
		            echo "Removing the old container"
		            sshCommand remote: remote, command: 'docker rm home_automation || true'
		            echo "Starting the latest docker container"
		            sshCommand remote: remote, command: 'docker run --name home_automation -p 9090:9090 -d sethusuresh/home_automation'
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







