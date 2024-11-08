# Stage 2: Runtime for user-service
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app/user-service

# Copy the built JAR for user-service
COPY --from=water_server-builder /app/user-service/target/app.jar .

# Set the active Spring profile (if needed)
ENV SPRING_PROFILES_ACTIVE=dev

# Expose the port for user-service
EXPOSE 9050

# Run the user-service application
ENTRYPOINT ["java", "-jar", "app.jar"]