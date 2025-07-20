# Use Eclipse Temurin JDK 20 base image (Alpine variant)
FROM eclipse-temurin:20-jre-alpine

# Define the path to your WAR file (build-time argument)
ARG JAR_FILE=build/libs/fake-rest-service-0.0.1-SNAPSHOT.jar

# Add the WAR file to the container
ADD ${JAR_FILE} fake-rest-service-0.0.1-SNAPSHOT.jar

# Set the container entrypoint
ENTRYPOINT ["java", "-jar", "fake-rest-service-0.0.1-SNAPSHOT.jar"]

# Expose the application port
EXPOSE 8081

FROM eclipse-temurin:20-jre-alpine

MAINTAINER haritech.com

ENV APP_HOME=/app
WORKDIR ${APP_HOME}

# Add non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the built jar from builder stage
COPY --from=builder build/libs/*.jar fake-rest-service.jar

# Expose application port
EXPOSE 8080

# Optional: Health check
HEALTHCHECK --interval=30s --timeout=5s \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
