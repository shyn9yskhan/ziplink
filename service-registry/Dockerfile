FROM openjdk:17
WORKDIR /app
COPY target/service-registry-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]