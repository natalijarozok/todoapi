#docker run --name todoapi_postgres12_2 -e POSTGRES_PASSWORD=mysecretpassword -it -p 5432:5432  postgres:12
# Part 1: Build the app using Maven
FROM maven:3.6.0-jdk-8-alpine AS MAVEN_BUILD
WORKDIR /build/
ADD pom.xml /build/
ADD . /build/
RUN mvn package -Dmaven.test.skip=true

# Part 2: use the JAR file used in the first part and copy it across ready to RUN
FROM openjdk:8-jdk-alpine
WORKDIR /root/
COPY --from=MAVEN_BUILD /build/target/*.jar app.jar
ENTRYPOINT ["java","-jar","./app.jar"]