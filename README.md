# 🧪 Test Automation Framework

This project is a Java-based Cucumber test automation framework using **Maven**, **Cucumber**, and **Allure** for reporting. It supports execution inside **Docker** and integrates with **GitHub Actions** for CI/CD.

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
- (Optional) [Allure CLI](https://docs.qameta.io/allure/#_installing_a_commandline)

---
### 2. Clone the Repository

## 📦 Project Structure

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
allure serve target/allure-results
```

Or generate static HTML:

```bash
allure generate target/allure-results -o target/allure-report
allure open target/allure-report
```

---

## 🐳 Running Tests in Docker

1. Build Docker Image

```bash
docker build -t test-runner .
```

2. Run Tests in Container

```bash
docker run --p 8080:8080 test-runner
```

3. Open report
   http://localhost:8080/

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
2. Select the latest workflow run
3. Download the `allure-report` artifact

---