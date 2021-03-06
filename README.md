# SafetyNet - Alerts
An application which send informations about people to emergency services.

### Prerequisites
- Java 1.8
- Maven 3.6.0

### Installing
Import the project in your favorite IDE.

Production data are stored in `resources/data.json`. Test Data are in `resources/data-test.json`.

Fill `data.json` with your data.

In the two `application.properties` files located in `src/main/resources` and `src/test/resources`, the key `storage.file` contains the data files path.

### Testing
Go to the root repository of the application:

Execute `mvn clean test` to run unit tests.

Execute `mvn verify` to run unit and integration tests.

### Documentation and reports
Execute `mvn site` to generate reports (Surefire, Jacoco) and Javadoc.

Reports and documentation's link is: `file:///YOUR/ROOT/PROJECT/SafetyNet-Alerts/target/site/index.html`

### Building the project
Execute `mvn package` to generate an executable.

### Running App
Go to the `target` repository and run the executable:

`java -jar SafetyNet-Alerts-1.3.0-SNAPSHOT.jar`.

You can test the API with a tool like Postman. 

If you move the jar file, you need to move your data file `data.json` in a folder named `resources` located in the same repository that the jar file.

### Monitoring
Info, trace, metrics and more are available at `http://localhost:9001/actuator`.

### API documentation
Swagger2 provides an API documentation at `http://localhost:8080/swagger-ui/index.html`.

