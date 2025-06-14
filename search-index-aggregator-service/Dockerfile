FROM openjdk:17
WORKDIR /app

COPY target/search-index-aggregator-service-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh ./
RUN chmod +x wait-for-it.sh

EXPOSE 8089

ENTRYPOINT ["./wait-for-it.sh","elasticsearch:9200","--strict","--timeout=600","--","./wait-for-it.sh","kafka:9092","--strict","--timeout=600","--","java","-jar","app.jar"]
