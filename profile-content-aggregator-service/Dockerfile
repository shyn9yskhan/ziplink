FROM openjdk:17
WORKDIR /app
COPY target/profile-content-aggregator-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]