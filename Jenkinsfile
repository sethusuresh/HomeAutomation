def remote = [:]
remote.name = "raspberrypi"
remote.host = "ssautohome.hopto.org"
remote.allowAnyHosts = true
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
        stage('Copying JAR') {
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
			            sshScript remote: remote, script: 'docker build /home/jenkins -t home_automation'
			            sshScript remote: remote, script: 'docker run --name home_automation -p 9090:9090 -d home_automation'
			        /*stage("SSH Steps Rocks!") {
			            //writeFile file: 'test.sh', text: 'ls'
			            //sshPut remote: remote, from: 'test.sh', into: '.'
			            //sshGet remote: remote, from: 'test.sh', into: 'test_new.sh', override: true
			            //sshRemove remote: remote, path: 'test.sh'
			        }*/
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







