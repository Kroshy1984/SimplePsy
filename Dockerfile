FROM openjdk:17-alpine as build

WORKDIR /app

COPY . .

RUN ./gradlew clean build --no-daemon

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=build /app/build/libs/simplepsy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM maven:3.8.5-openjdk-17 as build
#
#WORKDIR /app
#
#ADD . .
#
#RUN ["mvn", "clean", "package"]
#
#FROM tomcat:10.1-jdk21 AS tomcat
#
#WORKDIR /usr/local/tomcat/webapps
#
#COPY --from=build /app/target/simplepsy-0.0.1-SNAPSHOT.war simplepsy.war
#
#EXPOSE 8080
