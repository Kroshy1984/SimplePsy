FROM openjdk:17-alpine as build

WORKDIR /app

COPY . .

RUN ./gradlew clean build --no-daemon

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=build /app/build/libs/SimplePsy-Specialist-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
