FROM gradle:8.14.4-jdk21 AS builder

WORKDIR /app

COPY --chown=gradle:gradle . /app

RUN gradle bootJar --no-daemon

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
