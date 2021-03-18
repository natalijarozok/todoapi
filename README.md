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
- mvn compile.  
  Please, notice that, if you run *mvn build* command, build will fail because of failing integration tests. To make integration tests pass we need to have running PostgerSQL service.  
  Please, see section **[Docker containers](#Docker-containers)** to get information on how to run docker container with PostgreSQL database.


### [Docker containers](#Docker-containers)

Application is supplied with docker-compose.yaml file.
It builds images and runs 2 docker containers with
- Todoapi application (container name is *todoapi*)
    - exposes port 8080
    - application is accessible at url *http://localhost:8080/api/v1/tasks*

- PostgreSQL (container name is *todoapi_postgres*)
    - exposes port 5432
    - 2 empty databases with names *todo_api* and *todo_api_test* are created automatically
    - databases can be accessed from local pc at *jdbc:postgresql://localhost:5432/postgres* via any appropriate db client.

To start containers, run *docker-compose up* command from the todoapi project root.

### Api documentation

Swagger UI page will be available after successful startup at
http://localhost:8080/swagger-ui.html

Documentation in json format will be available at url http://localhost:8080/v3/api-docs  
Documentation in YAML format will be available on the path  /v3/api-docs.yaml