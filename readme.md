# Springboot(Kotlin) and Mysql
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
### Postman Document
The postman json is found at `docs/postman_collection.json`
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

## Upload function
Go to either:
  - http://localhost:8080/upload
  - http://localhost:8080/swagger 

For testing of the upload function. \
The sample data for files uploading are found at `sample-data`

## Additional Information
### File Uploading Design
File processing needs to be asynchronous. 
1) After the user has uploaded the file, the contents of the file is validated. 
Depending on the size of file, this validation may take some time to complete. 
This is a worthwhile trade-off as most file content errors can be caught in this step. 
2) After the file is validated, a response is given to the client and file record is generated. 
The backend will spawn another thread to push data into the database. 
3) After all records are successfully saved into the database, the backend will update the file record
status to *__COMPLETED__*.




