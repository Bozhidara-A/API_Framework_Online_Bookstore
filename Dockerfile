# Use Maven with Java 17
FROM maven:3.9.4-eclipse-temurin-17

# Install dependencies for Allure CLI
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://github.com/allure-framework/allure2/releases/download/2.24.0/allure-2.24.0.zip && \
    unzip allure-2.24.0.zip -d /opt/ && \
    ln -s /opt/allure-2.24.0/bin/allure /usr/bin/allure

# Install python3
RUN apt-get update && apt-get install -y python3

# Set working directory
WORKDIR /app
# Copy only Maven descriptor files first
COPY pom.xml /app
# Pre-fetch dependencies
RUN mvn dependency:go-offline
# Copy the rest of the project
COPY . /app

# Add entrypoint script
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]