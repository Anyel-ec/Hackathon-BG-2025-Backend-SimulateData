# Base image with JDK 17
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR and necessary files
COPY app.jar app.jar

# Copy the .env file if needed

# Expose the application port
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]