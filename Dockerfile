FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el jar construido de la aplicación al directorio de trabajo del contenedor
COPY target/travel-paths-manager.jar travel-paths-manager.jar

# Define el comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "travel-paths-manager.jar"]