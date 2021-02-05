FROM maven:3.6.0-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM adoptopenjdk/openjdk11:alpine
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]