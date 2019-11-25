FROM openjdk:8
ADD target/sms-docker.war sms-docker.war
EXPOSE 8010
ENTRYPOINT ["java","-jar","sms-docker.war"] 
