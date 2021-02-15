# File uploading using Springboot(Kotlin) and mysql
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
Download at http://localhost:8080/v3/api-docs
### How to import to postman
Postman supports importing of OpenApi 3.0 Specifications \
The documentations can be found at `docs/api-docs.json` \
To support fast development, import using link http://localhost:8080/v3/api-docs in postman

## Unit test
The application uses JUnit and mockit framework for testing

## Sample data
The sample data for files uploading are found at `sample-data`

