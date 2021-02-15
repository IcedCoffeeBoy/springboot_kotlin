FROM adoptopenjdk/maven-openjdk11 AS maven
WORKDIR /app
ADD ./pom.xml /app
RUN mvn dependency:go-offline --fail-never
RUN mvn dependency:resolve-plugins --fail-never

FROM maven as builder
ADD src /app/src
RUN mvn -DskipTests=true package

FROM openjdk:11-jre-slim as app
WORKDIR /opt/spring
COPY --from=builder /app/target/fileupload-0.0.1-SNAPSHOT.jar fileupload-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/opt/spring/fileupload-0.0.1-SNAPSHOT.jar"]

