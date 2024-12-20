FROM maven:3.8.5-openjdk-17

COPY . /tweet-service/
WORKDIR /tweet-service/
RUN ls
RUN mvn clean install -DskipTests
EXPOSE 9002
#CMD ["sleep","3600"]

# ARG JAR_FILE=*.jar
ENTRYPOINT ["java", "-jar", "target/tweet-service-0.0.1-SNAPSHOT.jar"]


# FROM eclipse-temurin

# WORKDIR /app
 
# COPY target/*.jar /app/tweetservice.jar

# EXPOSE 9002

# ENTRYPOINT ["java", "-jar", "/app/tweetservice.jar"]
