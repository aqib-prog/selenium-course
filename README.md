# Selenium Automation Framework 

A production-grade web automation framework built with Java, Selenium, and TestNG.

## Tech Stack
- Java 23
- Selenium WebDriver 4.20
- TestNG
- Maven
- Cucumber BDD
- GitHub Actions (CI/CD)

## What This Covers
- Page Object Model (POM)
- Explicit & Implicit Waits
- Cross Browser Testing (Chrome + Safari)
- Parallel Execution (ThreadLocal)
- Data Driven Testing (@DataProvider)
- Screenshots on Failure
- Cucumber BDD (Gherkin)
- Headless execution in CI/CD

## How to Run

### Setup
```bash
mvn install
```

### Run Tests
```bash
mvn test
```

### Run Specific Suite
```bash
mvn test -Dsurefire.suiteXmlFiles=testing.xml
```

## Project Structure
