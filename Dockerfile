# Base image with JDK 17
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR and necessary files
COPY monitoring-1.0.0-RELEASE.jar monitoring-1.0.0-RELEASE.jar

# Copy the .env file if needed
COPY .env .env

# Expose the application port
EXPOSE 8080