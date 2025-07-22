# ----------- BUILD STAGE-----------
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# Copy all files and folders from the current directory on your host machine 
# into the current working directory inside the container (/app in this case) COPY <source> <destination>
COPY . .

# Compile app with Maven Wrapper, skipping tests
RUN ./mvnw clean package -DskipTests

# ----------- EXECUTION STAGE -----------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy generated JAR from previous stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port
EXPOSE 5000

# Execute app
ENTRYPOINT ["java", "-jar", "app.jar"]