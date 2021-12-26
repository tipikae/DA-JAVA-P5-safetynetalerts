# SafetyNet - Alerts
An application which send informations about people to emergency services.

### Prerequisites
- Java 1.8
- Maven 3.6.0

### Installing
Import the project in your favorite IDE.

Data are stored in a JSON file `data.json` located in `/storage` repository.

Update the data with your data.

### Testing
Execute `mvn clean test` to run unit tests.

Execute `mvn verify` to run unit and integration tests.

### Documentation and reports
Execute `mvn site` to generate reports (Surefire, Jacoco) an Javadoc.

Reports and documentation's link is: `http://localhost:9000/YOUR/ROOT/PROJECT/SafetyNet-Alerts/target/site/project-reports.html` 

### Building the project
Execute `mvn package` to generate an executable.

### Running App
Launch the app and access the informations with a tool like Postman.

