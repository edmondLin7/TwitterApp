FROM mysql:latest

CMD ["create", "database", "twitter_microservices"]

FROM openjdk:17

CMD ["mysql", "-u", "root", "-p", "314159"]

ARG JAR_FILE=target/*.jar

VOLUME /tmp


COPY ${JAR_FILE} /app/tweetservice.jar

EXPOSE 9002

ENTRYPOINT ["java", "-jar", "/app/tweetservice.jar"]

