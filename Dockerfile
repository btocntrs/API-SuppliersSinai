# Usamos una imagen base de OpenJDK 17
FROM openjdk:21-jdk

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

ARG JAR_FILE=build/libs/ProveedoresSinaiAPI-0.0.1.jar

# Copiamos el archivo JAR generado por Spring Boot al directorio de trabajo del contenedor
COPY ${JAR_FILE} /app/ProveedoresSinaiAPI-0.0.1.jar

# Exponemos el puerto que usa Spring Boot (por defecto 8080)
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/ProveedoresSinaiAPI-0.0.1.jar"]