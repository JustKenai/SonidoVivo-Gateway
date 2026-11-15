# ---------- Etapa 1: compilar con Maven ----------
FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /src
COPY . .
RUN mvn -B -DskipTests package

# ---------- Etapa 2: ejecutar solo el .jar ----------
FROM eclipse-temurin:21-jre-noble
WORKDIR /app
COPY --from=build /src/target/*.jar /app/app.jar
USER 10001:10001
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]