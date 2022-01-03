# SafetyNet - Alerts
An application which send informations about people to emergency services.

### Prerequisites
- Java 1.8
- Maven 3.6.0

### Installing
Import the project in your favorite IDE.

Create 2 files: `data.json` and `data-test.json` in a location of your choice (outside the classpath's application).

Fill `data.json` with your data. You have an example in `resources/data.json`.

Then in the 2 `application.properties` files located in `src/main/resources` and `src/test/resources`, update the key `storage.file` with the path of your JSON files.

### Testing
Go to the root repository of the application:

Execute `mvn clean test` to run unit tests.

Execute `mvn verify` to run unit and integration tests.

### Documentation and reports
Execute `mvn site` to generate reports (Surefire, Jacoco) an Javadoc.

Reports and documentation's link is: `file:///YOUR/ROOT/PROJECT/SafetyNet-Alerts/target/site/index.html`

### Building the project
Execute `mvn package` to generate an executable.

### Running App
Go to the `target` repository and run the executable:

`java -jar SafetyNet-Alerts-0.0.1-SNAPSHOT.jar`.

You can test the API with a tool like Postman. 

### Monitoring
Info, trace, metrics and more are available at `http://localhost:9001/actuator`.

### API documentation
Swagger2 provides an API documentation at `http://localhost:8080/swagger-ui/index.html`.

