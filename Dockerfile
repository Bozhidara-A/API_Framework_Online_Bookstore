# Use Maven with Java 17
FROM maven:3.9.4-eclipse-temurin-17

# Install dependencies for Allure CLI
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://github.com/allure-framework/allure2/releases/download/2.24.0/allure-2.24.0.zip && \
    unzip allure-2.24.0.zip -d /opt/ && \
    ln -s /opt/allure-2.24.0/bin/allure /usr/bin/allure

# Set working directory
WORKDIR /app

# Copy project files
COPY . /app

# Cache dependencies
RUN mvn dependency:go-offline

# Default command: run tests and generate report
CMD mvn clean test && allure generate target/allure-results -o target/allure-report