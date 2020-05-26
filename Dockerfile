FROM openjdk
COPY ./jar/homeAutomation.jar /usr/src/homeAutomation.jar
CMD java -jar /usr/src/homeAutomation.jar