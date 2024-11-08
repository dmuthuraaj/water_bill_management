# Stage 2: Runtime for device-service
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app/bill-service

# Copy the built JAR for device-service
COPY --from=water_server-builder /app/bill-service/target/app.jar .

# Set the active Spring profile (if needed)
ENV SPRING_PROFILES_ACTIVE=dev

# Expose the port for device-service
EXPOSE 9052

# Run the device-service application
ENTRYPOINT ["java", "-jar", "app.jar"]