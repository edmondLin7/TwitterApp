

FROM eclipse-temurin

WORKDIR /app
 
COPY target/*.jar /app/tweetservice.jar

EXPOSE 9002

ENTRYPOINT ["java", "-jar", "/app/tweetservice.jar"]
