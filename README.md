# Optimal Travel Paths

This project is a RESTful web service for travel optimization, allowing storing information about stations and paths and finding the optimal path between two stations while minimizing costs.

## Requirements

- Java 11 o superior
- Docker (opcional, para ejecutar la aplicación en un contenedor)

## Configuration

No special configuration is required. The project is set up to run with default settings.

## Compilation

To compile the project, run the following command: mvn clean install

This will compile the project and generate an executable JAR file in the target directory.


## Usage
Una vez que la aplicación esté en funcionamiento, puedes acceder a los siguientes endpoints RESTful:

- `PUT /stations/$station_id`: Add a new station.
- `PUT /path`: Add a new path. Body: { "cost":double, "source_id":long, "destination_id":long }
- `GET /paths/$source_id/$destination_id`:  Find the optimal path between two stations. Returns:{ "path": [long], "cost" : double }

For more details on how to interact with these endpoints, refer to the API documentation.

## API Documentation
The API documentation is available at the /swagger-ui.html endpoint once the application is running.


## DOCKER
Open a terminal in the root directory of your project where the Dockerfile is located. Run the following command to build the Docker image:

docker build -t optimal-travel-paths 


Once the Docker image is successfully built, you can run the Docker container with the following command:

docker run -d -p 8080:8080 optimal-travel-paths


