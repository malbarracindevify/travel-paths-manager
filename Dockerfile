FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar of the application to the container's working directory
COPY target/travel-paths-manager.jar travel-paths-manager.jar


# Defines the command to run the application when the container starts
CMD ["java", "-jar", "travel-paths-manager.jar"]