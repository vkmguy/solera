# springboot-Register-Applicaiton
## Implementation of Solera Task

## The solution consists of following components:
- Register Entity - responsible for mapping DB Table
- Register Repository - responsible for implementing JPA CRUD operations with DB
- Register Service - responsible for retrieving data from Repository
- Resigter Controller - Responsible for providing API endpoints for Register service

## Implementation limitations
Due to time limitations the provided implementation is not perfect, the main focus is to 
provide a working REST API's with simple Arkitecture
In particular the following still can be improved:
- Add input validaiton
- Add Proper exceptions handing by REST end-points. For now the we have implemented a simple ControllerAdvice to handle one exception
- Add Testcases, for testing all scenarios

## Build instructions
Maven is used to build the project
After Maven installed and project is check out from the repository, execute the following command:
-- mvn clean install

### Run instructions:
In command line execute:
-- java -jar registers-0.0.1-SNAPSHOT.jar

### The following REST endpoints are exposed:
    
    ```
    - Recharge Register:
     GET /registers/recharge/<REGISTER_NAME>?amount=<AMOUNT>
    * Returns Updated Register Body
    ```
    
    ```
    - Transfer Between Registers:
     GET /registers/transfer/<AMOUNT>?source=<SOURCE_REGISTER_NAME>&destination=<DESTINATION_REGISTER_NAME>
    * Returns Both Updated Register Body
     ```

    ```
    - Get Registers:
     GET /registers
    * Returns All Registers in DB
    ```

### Configuration:
- server.port - port number which this service is listening to. Default - 8080
- spring.jpa.datasource.url - Database url for storing Register Data
- spring.jpa.database-platform - The database Dialect to use. Default - org.hibernate.dialect.PostgreSQLDialect