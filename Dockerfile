FROM gradle:jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle bootJar


FROM amazoncorretto:17-alpine AS runtime
WORKDIR /app

ENV SPRING_ACTIVE_PROFILES "prod"

EXPOSE 8080

ARG JAR_FILE=/app/build/libs/*.jar
COPY --from=build ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]