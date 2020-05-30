FROM raspbian/stretch
RUN sudo apt-get update && sudo apt-get -y upgrade && apt-get -y dist-upgrade
RUN sudo apt install -y default-jdk
RUN java -version
COPY ./jar/homeAutomation.jar /usr/src/homeAutomation.jar
CMD java -jar /usr/src/homeAutomation.jar