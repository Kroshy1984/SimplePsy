FROM maven:3.8.5-openjdk-17 as build

WORKDIR /app

ADD . .

RUN ["mvn", "clean", "package"]

FROM tomcat:10.1-jdk21 AS tomcat

WORKDIR /usr/local/tomcat/webapps

COPY --from=build /app/target/simplepsy-0.0.1-SNAPSHOT.war simplepsy.war

EXPOSE 8080
