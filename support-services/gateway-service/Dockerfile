# Stage 2: Runtime for gateway-service
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app/gateway-service

# Copy the built JAR for gateway-service
COPY --from=water_server-builder /app/support-services/gateway-service/target/app.jar .

# Set the active Spring profile (if needed)
ENV SPRING_PROFILES_ACTIVE=dev

# Expose the port for gateway-service
EXPOSE 9001

# Run the gateway-service application
ENTRYPOINT ["java", "-jar", "app.jar"]