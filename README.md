# Orchestrator
Microservice orchestrator for Utopia Airlines. Works in conjunction with the following microservices:
* [Auth](https://github.com/jms-smoothstack-utopia/ss-utopia-auth), port 8089
* [Customers](https://github.com/jms-smoothstack-utopia/ss-utopia-customers), port 8081
* [Email](https://github.com/jms-smoothstack-utopia/ss-utopia-email), port 6900
* [Tickets/Bookings](https://github.com/jms-smoothstack-utopia/ss-utopia-tickets), port 8082
* [Flights/Airports/Airplanes](https://github.com/jms-smoothstack-utopia/ss-utopia-flights), port 8083

All microservices accept and return either JSON or XML.

## Installation
The orchestrator is a Spring application and requires [Maven](https://maven.apache.org/download.cgi). After installation, use `mvn spring-boot:run` to build and launch the application.

The orchestrator requires the auth microservice for most services (see authentication requirements below) as well as all of the rest of the microservices above that you wish to run. It is configured in [application.properties](https://github.com/jms-smoothstack-utopia/ss-utopia-orchestrator/blob/dev/src/main/resources/application.properties) to run on port 8080, and the individual microservice URLs are located there as well. If you change a microservice's port or location, make sure to update your install's application.properties accordingly.

The microservices require a MySQL database, which is to be hosted locally by default configuration. See their readmes for more details on each.

## API Specification
API specifications can be retrieved via OpenAPI while the service is running by going to `http://localhost:8080/api-docs` for the most up-to-date version. A copy is stored locally in the root folder as [api-docs.json](./api-docs.json) but is not guaranteed to be updated with changes to the service.

## Authentication requirements
POST requests to the authentication endpoints, and PUT requests for account registration confirmation, do not require authentication. All other endpoints require a valid JWT authentication token in the request header; without this, a 403 error will be returned.

