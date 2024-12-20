FROM maven:3.8.5-openjdk-17

COPY . /cloud-gateway/
WORKDIR /cloud-gateway/
RUN ls
RUN mvn clean install -DskipTests
EXPOSE 9090
CMD ["sleep", "3600"]
#ARG JAR_FILE=target/*.jar
ENTRYPOINT ["java", "-jar", "target/cloud-gateway-0.0.1-SNAPSHOT.jar"]

# FROM openjdk:19-jdk

# WORKDIR /app
 
# COPY target/*.jar /app/cloudgateway.jar

# EXPOSE 9090

# ENTRYPOINT ["java", "-jar", "/app/cloudgateway.jar"]
