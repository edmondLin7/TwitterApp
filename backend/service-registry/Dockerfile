FROM openjdk:17

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-registry.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/service-registry.jar"]