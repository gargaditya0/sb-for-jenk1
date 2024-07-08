FROM alpine/java:22-jdk
LABEL authors="garga"

WORKDIR /app

COPY ./target/demo-0.0.1.jar /app

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "demo-0.0.1.jar"]