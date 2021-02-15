# File uploading using Springboot(Kotlin) and Mysql
![Java CI with Maven](https://github.com/IcedCoffeeBoy/springboot_kotlin/workflows/Java%20CI%20with%20Maven/badge.svg)
___
A sample SpringBoot to do a large file uploading and searching
## Quick Start
*Set up requires __Docker__ to be installed*\
Run the following commands:
```bazaar
docker-compose up -d 
```
## Api Definitions
### Swagger UI
The project is set up with swagger.\
To open the swagger: http://localhost:8080/swagger
### OpenApi 3.0 Specification
The project implemented *OpenApi 3.0 Specifications* \
Download at http://localhost:8080/v3/api-docs \
Note: Postman supports importing of OpenApi 3.0 Specification JSON 

## Unit tests
The application uses JUnit and mockit framework for testing. 
### Design Considerations
The unit test uses in-memory H2 database. It is able to be run independently 
without the presence of external database. This helps to solve issues when running 
unit tests in devops pipeline. 
### How to run the unit test
To run the test: 
```bazaar
mvn test 
```

## Sample data
The sample data for files uploading are found at `sample-data`

## Additional Information
### File Uploading Design




