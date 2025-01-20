FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/spring-vue-0.0.1-SNAPSHOT.jar /app/spring-vue.jar

# Copy the static files into the container
COPY src/main/resources/static /app/static

# Expose the port your app will run on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/spring-vue.jar"]
