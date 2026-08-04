OrangeHRM Hybrid Automation Framework
📌 Project Overview

This project is a Hybrid Test Automation Framework developed for the OrangeHRM Demo Application using Selenium WebDriver, Java, and TestNG. It follows the Page Object Model (POM) design pattern and supports Data-Driven Testing, reusable utilities, and parallel execution.

🚀 Technologies Used
Java 17
Selenium WebDriver
TestNG
Maven
Apache POI (Excel)
WebDriverManager
Page Object Model (POM)
ThreadLocal WebDriver
Git & GitHub

📂 Framework Features
Page Object Model (POM)
Hybrid Framework Architecture
Data-Driven Testing using Excel
Parallel Test Execution
ThreadLocal WebDriver
Reusable Base Page
Reusable Browser Utilities
Explicit Waits
Cross Browser Ready
Maven Build Management
TestNG Reports

🧪 Test Scenarios Automated
***Login Module***
Valid Login
Invalid Login
Blank Username
Blank Password
Invalid Credentials Validation

***Admin Module***
Add User
Edit User
Delete User

***Organization Module***
Add Location
Add Company
Add Structure

***Job Module***
Job Titles
Pay Grades
Employment Status
Job Categories

***PIM Module***
Add Employee
Search Employee
Update Employee Details

***Social Media Links***
Verify LinkedIn Link
Verify Facebook Link
Verify Twitter/X Link
Verify YouTube Link
Validate New Tab Opens Successfully

📁 Project Structure
src
├── main
│   └── java
│       ├── base
│       ├── pages
│       └── utilities
│
└── test
    └── java
        ├── loginTest
        ├── jobModule
        ├── organizationModule
        ├── pimModule
        └── userManagementModule


▶️ How to Execute
Clone Repository
git clone https://github.com/Shubham1122k/OrangeHRM-Hybrid-Automation-Framework.git

Import Project
Import as an Existing Maven Project into Eclipse.

Execute Tests

Run
testng.xml

or execute using Maven
mvn clean test

⭐ Future Enhancements
Jenkins CI/CD Integration
Extent Reports
Allure Reports
Docker Integration
GitHub Actions
Cross Browser Execution
Cloud Execution (BrowserStack/Sauce Labs)
