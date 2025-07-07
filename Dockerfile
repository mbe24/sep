# --- Stage 1: The Builder ---
FROM gradle:8.4.0-jdk17-alpine AS builder

WORKDIR /app

# Copy the Gradle wrapper and build scripts first.
# This leverages Docker's layer caching. Dependencies won't be re-downloaded
# on every code change, only when the build script changes.
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle
RUN chmod +x ./gradlew

COPY src ./src
# The --no-daemon flag is recommended for CI/CD environments like Docker builds.
RUN ./gradlew build --no-daemon


# --- Stage 2: The Final Image ---
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]