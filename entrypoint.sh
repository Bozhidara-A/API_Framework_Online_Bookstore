#!/bin/bash
set -e

echo "Running tests..."
mvn clean test || true

echo "Generating Allure report..."
allure generate target/allure-results -o target/allure-report || exit 1

# Detect CI environment
if [ "$CI" = "true" ]; then
  RUN_MODE="ci"
else
  RUN_MODE="local"
fi

echo "Detected run mode: $RUN_MODE"

# Conditional server start
if [ "$RUN_MODE" = "local" ]; then
  echo "Starting server at http://localhost:8080"
  python3 -m http.server 8080 --directory target/allure-report
else
  echo "CI mode detected — skipping server startup."
fi