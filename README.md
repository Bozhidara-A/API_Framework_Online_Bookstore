# Test Automation Framework

This project is a Java-based test automation framework using **Maven**, **Cucumber**, **RestAssured** and **Allure** for reporting. It supports execution inside **Docker** and integrates with **GitHub Actions** for CI/CD.

---

## 🚀 Features

- ✅ BDD-style testing with Cucumber
- 🧪 Test execution via Maven
- 📊 Allure reporting
- 🐳 Dockerized test runs
- 🔁 CI/CD with GitHub Actions

---

## 🛠️ Setup Instructions

### 1. Prerequisites
Make sure you have the following installed:

- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [Allure CLI](https://docs.qameta.io/allure/#_installing_a_commandline) (Optional)

---

### 2. Clone the Repository
```bash
git clone https://github.com/Bozhidara-A/API_Framework_Online_Bookstore.git
```

---
### 📦 Project Structure

```
.
├── pom.xml
├── Dockerfile
├── src/main/java/com.avenga/...       # Step definitions & helpers
├── src/test/resources/...             # Feature and config files
├── src/test/java/runners/...          # Test runners
├── target/allure-results/             # Generated test results
└── .github/workflows/ci-pipeline.yml  # GitHub Actions workflow

```
---

## ▶️ Running Tests Locally

### Run Tests with Maven

```bash
mvn clean test
```

This will execute the Cucumber tests and generate Allure results in:

```
target/allure-results/
```

### Generate Allure Report (Optional)

```bash
mvn allure:serve 
```
---
### Running the tests with configurations
-  Using Command-Line Argument
```bash
mvn test "-Dbase.url=https://example.net"
```
- Using Environment Variable
```bash
$env:BASE_URL = "https://example.net"
```
---

## 🐳 Running Tests in Docker

### 1. Build Docker Image

```bash
docker build -t test-suite .
```

### 2. Run Tests in Container

```bash
docker run -p 8080:8080 test-suite
```

### 3. Open report
   Open http://localhost:8080/

---
### Running the tests with configurations
-  Using Command-Line Argument
```bash
docker run test-suite mvn test -Dbase.url=https://api.example.com
```
- Using Environment Variable
```bash
docker run -e BASE_URL=https://api.example.com test-suite
```
---

## 🔁 CI/CD with GitHub Actions

The workflow file is located at:

```
.github/workflows/ci-pipeline.yml
```

### What It Does

- Builds the Docker image
- Runs tests inside the container
- Copies Allure results
- Generates and uploads the Allure report

### View the Report

After each run:

1. Go to the **Actions** tab in GitHub
2. Select the latest workflow run - "pages build and deployment"
3. Open the link from deploy phase

---