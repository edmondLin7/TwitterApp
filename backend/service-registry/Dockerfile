FROM maven:3.8.5-openjdk-17

COPY . /service-registry/
WORKDIR /service-registry/
RUN ls
RUN mvn clean install -DskipTests
EXPOSE 8761

# CMD [ "sleep", "3600" ]
# RUN $JAR_FILE=target/*.jar
# RUN "echo ${JAR_FILE}"
# CMD ["sleep","3600"]
#ENTRYPOINT ["java", "-jar", JAR_FILE]
# ARG JAR_FILE=$(target/*.jar)
# RUN export JAR_FILE=$(target/*.jar)
# COPY ${JAR_FILE} service-registry.jar
# EXPOSE 8761
ENTRYPOINT ["java", "-jar", "target/service-registry-0.0.1-SNAPSHOT.jar"]

