FROM openjdk:19-jdk

WORKDIR /app
 
COPY target/*.jar /app/cloudgateway.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app/cloudgateway.jar"]
