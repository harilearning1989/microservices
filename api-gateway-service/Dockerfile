# Use a JDK 20 base image
#FROM eclipse-temurin:20-jdk-alpine
# Use a compatible Java 20 image with ARM64 support
FROM eclipse-temurin:20-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file to the container
COPY build/libs/api-gateway-service-0.0.1-SNAPSHOT.jar api-gateway-service.jar

# Expose port 8082
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "api-gateway-service.jar"]

#docker build -t api-gateway-service:1.0.0 .
#docker run -p 8082:8082 fake-rest-service:1.0.0

#kubectl apply -f deployment.yaml
#kubectl apply -f service.yaml
#kubectl apply -f configmap.yaml
#kubectl apply -f secrets.yaml
#kubectl get pods

# View cluster info
#kubectl cluster-info

# View all resources in the cluster
#kubectl get all --all-namespaces

