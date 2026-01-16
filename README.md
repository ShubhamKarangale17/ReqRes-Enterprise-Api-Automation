# 🚀 ReqRes Enterprise API Automation Framework

![Build Status](https://github.com/<YOUR_GITHUB_USERNAME>/reqres-enterprise-api-automation/actions/workflows/maven.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-Framework-0A66C2)
![RestAssured](https://img.shields.io/badge/RestAssured-API%20Testing-6DB33F)
![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-2088FF?logo=githubactions&logoColor=white)
![Automation](https://img.shields.io/badge/Test%20Automation-Enterprise--Grade-brightgreen)
![API](https://img.shields.io/badge/API-RESTful-orange)

---

## 📌 Overview

The **ReqRes Enterprise API Automation Framework** is a **production-ready API test automation framework**
built using **Java, RestAssured, TestNG, and Maven**.

This framework automates end-to-end testing of REST APIs provided by the public **ReqRes** service and
demonstrates **real-world enterprise QA and DevOps best practices**.

It is fully integrated with **GitHub Actions CI/CD**, enabling automated execution of API tests on every
code push.

---

## 🛠️ Tech Stack

- **Language:** Java 17  
- **API Automation:** RestAssured  
- **Test Framework:** TestNG  
- **Build Tool:** Maven  
- **CI/CD:** GitHub Actions  
- **Design Approach:** Modular, scalable & reusable architecture  

---

## 📂 Project Structure

```text
reqres-enterprise-api-automation
├── pom.xml
├── testng.xml
├── .github
│   └── workflows
│       └── maven.yml
└── src
    └── test
        └── java
            └── com
                └── reqres
                    ├── api        # API test classes
                    ├── base       # Base test setup
                    ├── utils      # Utility helpers
                    └── config     # Configuration handling
```

## ✅ Automated APIs

- Login API
- Register API
- Get Users API
- Create User API
- Update User API
- Delete User API

## ▶️ Execution

Run tests using Maven:

```bash
mvn clean test


---

# 🚀 CI/CD Pipeline

```md
```
## 🚀 CI/CD Integration

This project includes a GitHub Actions pipeline that:

- Triggers on every push
- Sets up Java 17
- Executes `mvn clean test`
- Fails the build if any test fails

This ensures continuous validation of API quality.

## 🎯 Why This Framework?

- Follows real enterprise automation standards
- Demonstrates API testing + CI/CD integration
- Clean Maven & TestNG configuration
- Ready to scale for real backend systems

