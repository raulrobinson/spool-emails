# This Dockerfile is used in order to build a container that runs the Spring Boot application
# Build the image with:
# docker build -f docker/Dockerfile -t springboot/sample-demo .
# Then run the container using:
# docker run -i --rm -p 8080:8080 springboot/spool-emails
FROM quay.io/devfile/maven:3.8.1-openjdk-17-slim
WORKDIR /build

# Build dependency offline to streamline build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:11-jdk
COPY --from=0 /build/target/spool-emails-0.0.1-SNAPSHOT.jar /app/target/app.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app/target/app.jar", "--server.port=8080" ]
