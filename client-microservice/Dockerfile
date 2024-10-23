FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml /app/
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build ./app/target/*.jar app.jar
ARG EUREKA_SERVER=credit-eval-eureka-server
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "app.jar"]