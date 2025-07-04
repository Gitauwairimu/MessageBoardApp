# # FROM eclipse-temurin:17-jdk-jammy
# FROM eclipse-temurin:17.0.11_9-jdk-jammy
# RUN apt update && apt upgrade -y
# WORKDIR /app
# COPY target/webapp-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java", "-jar", "app.jar"]

# Use a smaller JRE-based image (no JDK tools)
FROM eclipse-temurin:17.0.11_9-jre-jammy

# Update OS packages and clean up
RUN apt update && apt upgrade -y && \
    apt install -y --no-install-recommends passwd && \
    rm -rf /var/lib/apt/lists/*

# Create app directory first
WORKDIR /app

# Create non-root user and set permissions
RUN groupadd -r appuser && \
    useradd -r -g appuser appuser && \
    chown appuser:appuser /app

USER appuser

COPY --chown=appuser:appuser target/webapp-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]