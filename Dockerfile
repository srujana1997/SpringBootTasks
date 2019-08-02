
FROM java:8-jdk-alpine

COPY ./target/Muzix_Assignment-0.0.1-SNAPSHOT.jar app.jar

RUN sh -c 'touch app.jar'

ENTRYPOINT ["java","-jar","app.jar"]

