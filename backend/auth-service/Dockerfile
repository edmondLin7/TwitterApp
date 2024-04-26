FROM openjdk:19-jdk

WORKDIR /app
 
COPY target/*.jar /app/authservice.jar

EXPOSE 9001

ENTRYPOINT ["java", "-jar", "/app/authservice.jar"]
