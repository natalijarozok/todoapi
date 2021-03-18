## TODO-list api

TODO-list is a RESTful API that support the basic CRUD functionality for TODO-list.

### Specifications

Application

- is implemented as Maven project in Kotlin
- uses Spring Boot framework
- uses Hibernate ORM framework and PostgreSQL database for data persistence

### Requirements

Api requires Java 1.8

### IntelliJ

Project can be opened in IntelliJ IDE. Please, setup test and run configurations.

### Building from Source

To build the project run the following commands
- mvn clean
- mvn compile


### Docker

Application is supplied with DockerCompose file

### Api documentation

Swagger UI page will be available after successful startup at
http://localhost:8080/swagger-ui.html

Documentation in json format will be available at url http://localhost:8080/v3/api-docs  
Documentation in YAML format will be available on the path  /v3/api-docs.yaml