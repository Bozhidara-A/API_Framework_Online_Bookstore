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

# Expose the port used by Allure's Jetty server
EXPOSE 8080

# Run tests and serve the Allure report
RUN mvn clean test

# Serve the Allure report on 0.0.0.0 so Docker can expose it
CMD ["allure", "serve", "target/allure-results", "--host", "0.0.0.0", "--port", "8080"]
