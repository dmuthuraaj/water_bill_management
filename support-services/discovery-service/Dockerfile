# Stage 2: Runtime for identity-service
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app/discovery-service

# Copy the built JAR for discovery-service
COPY --from=water_server-builder /app/support-services/discovery-service/target/app.jar .

# Set the active Spring profile (if needed)
#ENV SPRING_PROFILES_ACTIVE=dev

# Expose the port for identity-service
EXPOSE 9000

# Run the identity-service application
ENTRYPOINT ["java", "-jar", "app.jar"]