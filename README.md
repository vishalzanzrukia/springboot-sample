# Sample Project
This is very simple spring boot project for demonstrating Spring boot capabilities. Please refer below documentation for more details about the technical stack, tools, local setup, test cases etc.

## Overview
* There are two entities called `Product` and `Client`
* Java generic is used wherever it's effective to reduce code duplication and so easy to maintain and scale
* `Product` can't be hard deleted, and so can be retrieved even after deleted with boolean flag called `includeDeleted`
* `Client` cab be hard deleted normally
* App is ready to run with pre-populated data

## Tools Used
### 1. Database
H2 database used, and actual data will be stored using file system in the /target folder. While running integration tests, the data will be stored in the memory. Please visit http://localhost:8080/h2-console/ to access database console.

### 2. DB Migration
Flyway is used for database migration, refer SQL files inside the resources/db/migration folder. There are some data pre-populated in the one of migrations so application will have always some data to play around on startup.

## Full Technical Stack
* Spring Boot 2.7.2 (latest from 2.x as of 06 Aug 2022)
* Java 11
* Maven
* H2
* Flyway
* JUnit 5
* Jacoco
* Lombok

## How-to Questions

### 1. How to run project in local?
There are multiple ways you can run the project based on your purpose/requirements. 
1. Using IDE: Just import project as Maven project in your favourite IDE and run as Application, no extra step required
2. Using Command Line without an external jar (recommended only for local): `mvn clean spring-boot:run`
3. Using Command Line with an external jar (recommended on cloud): a) `mvn clean package` b) `java -jar target/demo-0.0.1-SNAPSHOT.jar`

### 2. How to reset data same like first time project ran?
1. `mvn clean`
2. Run the application using your favourite method as described in above question

### 3. How to measure code coverage locally?
1. `mvn clean test`
2. Open `target/site/jacoco/index.html` with any browser and check report

## Notes
1. There are all layers (i.e. resource, service, repository) unit + integration test cases have been written and current code coverage with Jacoco is <b>97%</b> 
2. There are no spring profiles used as there were no such requirements, so it will be always "default" profile used
3. There are no create end-points implemented but as mentioned in DB migration section, there will be always some data pre-populated