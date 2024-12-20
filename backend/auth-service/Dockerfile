FROM maven:3.8.5-openjdk-17

COPY . /auth-service/
WORKDIR /auth-service/
RUN echo ls
RUN mvn clean install -DskipTests
EXPOSE 9001
# ARG JAR_FILE=target/*.jar
# CMD ["sleep", "3600"]
ENTRYPOINT ["java", "-jar", "target/auth-service-0.0.1-SNAPSHOT.jar"]

# FROM openjdk:19-jdk

# WORKDIR /app
 
# COPY target/*.jar /app/authservice.jar

# EXPOSE 9001

# ENTRYPOINT ["java", "-jar", "/app/authservice.jar"]
