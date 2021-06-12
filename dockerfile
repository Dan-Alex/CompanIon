FROM openjdk:14-jdk-alpine
VOLUME /tmp
EXPOSE 9090
ARG JAR=target/companion-0.0.1-SNAPSHOT.jar
ADD ${JAR} companion.jar
ENTRYPOINT ["java","-jar","/companion.jar"]