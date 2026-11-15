FROM eclipse-temurin:21-jre-alpine

LABEL authors="JJM"

WORKDIR /app

COPY target/gimnasio-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]