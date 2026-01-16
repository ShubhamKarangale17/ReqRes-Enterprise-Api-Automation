# 🚀 Quick Command Reference

## Essential Commands

### Run All Tests
```bash
mvn clean test
```
**Expected Output**: `Tests run: 31, Failures: 0, Errors: 0, Skipped: 0`

### Run Specific Test Class
```bash
mvn test -Dtest=LoginAPITest
```

### Run Specific Test Method
```bash
mvn test -Dtest=LoginAPITest#testLoginWithValidCredentials
```

### Compile Only (No Tests)
```bash
mvn clean compile
```

### Generate HTML Test Report
```bash
mvn surefire-report:report
# Open: target/site/surefire-report.html
```

---

## Verification Commands

### Verify Maven Build
```bash
mvn validate
```

### Check Compilation
```bash
mvn clean compile -q && echo "✅ Compilation SUCCESS"
```

### View Dependencies
```bash
mvn dependency:tree
```

### Check Surefire Configuration
```bash
mvn help:describe -Dplugin=surefire
```

### View Test Summary
```bash
mvn test -DfailIfNoTests=false
```

---

## Advanced Commands

### Run Tests with Verbose Output
```bash
mvn test -X
```

### Run Tests with Detailed Logging
```bash
mvn test -X 2>&1 | grep -i "test\|suite\|class"
```

### Skip Test Execution
```bash
mvn clean compile -DskipTests
```

### Force Maven to Update Dependencies
```bash
mvn clean install -U
```

### Clear Maven Cache
```bash
rmdir /s /q %USERPROFILE%\.m2\repository
mvn clean install
```

### Run Maven in Offline Mode
```bash
mvn test -o
```

---

## Debugging Commands

### List All Test Classes Found
```bash
mvn test -DfailIfNoTests=false -X 2>&1 | findstr "TestNG\|Suite\|Class"
```

### Run Single Test with Full Output
```bash
mvn test -Dtest=LoginAPITest -e
```

### Show Maven Properties
```bash
mvn help:describe -Dcapabilities
```

### View Final Effective POM
```bash
mvn help:effective-pom
```

### Check What Tests Will Run
```bash
mvn test -DdryRun
```

---

## Project-Specific Commands

### From Project Root
```bash
cd C:\Users\shubh\eclipse-workspace\API-CI-CD_Integration

# Run tests
mvn clean test

# Or specific test
mvn test -Dtest=LoginAPITest

# Or specific method
mvn test -Dtest=LoginAPITest#testLoginWithValidCredentials
```

---

## GitHub Actions Commands

### Simulate GitHub Actions Locally
```bash
# Install act (GitHub Actions local runner)
# choco install act-cli

# Run workflow locally
act push

# Run specific job
act push -j test
```

---

## Eclipse Commands (from command line)

### Refresh Maven Dependencies in Eclipse
```bash
mvn eclipse:eclipse
```

### Clean Eclipse Build
```bash
mvn eclipse:clean
```

### Generate Eclipse Project Files
```bash
mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true
```

---

## File Navigation Commands

### List All Java Test Files
```bash
dir /s /b src\test\java\*.java
```

### Count Total Tests
```bash
findstr /r "@Test" src\test\java\*.java | find /c "@Test"
```

### List All Configuration Files
```bash
dir /b *.xml *.md .classpath .project .gitignore
```

### Show Project Size
```bash
du -sh .
```

---

## CI/CD Commands

### Push to Trigger GitHub Actions
```bash
git add .
git commit -m "Run tests via GitHub Actions"
git push origin main
```

### Check GitHub Actions Status
```bash
# Visit: https://github.com/<username>/repo/actions
```

---

## Directory Structure

### View Project Tree
```bash
tree /F /A
```

### List Source Files Only
```bash
dir /s /b src\test\java\com\reqres
```

### List Configuration Files
```bash
dir /b pom.xml testng.xml .classpath .project
```

---

## Health Check Commands

### Full Project Health Check
```bash
echo === POM Validation === && ^
mvn validate && ^
echo === Compilation === && ^
mvn clean compile -q && ^
echo === Dependency Check === && ^
mvn dependency:resolve && ^
echo === Tests Discover === && ^
mvn test -DfailIfNoTests=false && ^
echo === All Checks Passed ===
```

### Quick Health Check
```bash
mvn clean compile && mvn test -Dtest=LoginAPITest && echo ✅ Project is healthy
```

---

## File Operations

### Backup Project
```bash
xcopy . backup_folder /E /I /Y
```

### Clean All Build Artifacts
```bash
rmdir /s /q target && ^
rmdir /s /q .settings && ^
del .classpath .project
```

### Reset to Fresh State
```bash
mvn clean
```

---

## Quick Troubleshooting

### Tests Not Running?
```bash
# Check TestNG config
mvn test -X 2>&1 | findstr "testng\|suite"

# Check classes found
mvn test -X 2>&1 | findstr "loading class"
```

### Compilation Error?
```bash
# Check Java version
java -version

# Force recompile
mvn clean compile -DskipTests
```

### Missing Dependencies?
```bash
# Resolve all
mvn dependency:resolve

# View tree
mvn dependency:tree

# Update
mvn clean install -U
```

### Eclipse Not Recognizing Tests?
```bash
# Right-click project in Eclipse
# Maven → Update Project → OK

# Or command line
mvn eclipse:clean eclipse:eclipse
```

---

## One-Liners

### Run all tests and show summary
```bash
mvn clean test 2>&1 | findstr "Tests run\|BUILD"
```

### Run tests and generate report in one command
```bash
mvn clean test && mvn surefire-report:report
```

### Run specific test with full output
```bash
mvn test -Dtest=LoginAPITest#testLoginWithValidCredentials -e
```

### Validate everything and run tests
```bash
mvn validate clean compile test
```

### Quick compilation check
```bash
mvn clean compile -q && echo ✅ Compiles OR echo ❌ Compilation failed
```

---

## Environment Setup

### Verify Java Installation
```bash
java -version
javac -version
```

### Verify Maven Installation
```bash
mvn --version
mvn -v
```

### Set JAVA_HOME (if needed)
```bash
set JAVA_HOME=C:\Program Files\Java\jdk-17.0.x
mvn test
```

---

## Useful Aliases (for .bashrc or PowerShell)

### PowerShell
```powershell
function mtest { mvn clean test }
function mcompile { mvn clean compile }
function mreport { mvn surefire-report:report }
function mvalidate { mvn validate }
```

### Bash
```bash
alias mtest="mvn clean test"
alias mcompile="mvn clean compile"
alias mreport="mvn surefire-report:report"
alias mvalidate="mvn validate"
```

---

## Most Common Command You'll Use

```bash
mvn clean test
```

**This single command**:
1. Cleans previous builds
2. Compiles source and test code
3. Runs all 31 tests via TestNG
4. Generates test reports
5. Shows final summary (Tests run: 31, Failures: 0)

---

## Tips & Tricks

### Skip Tests (Fast Compile)
```bash
mvn clean compile -DskipTests
```

### Fail Fast (Stop on First Failure)
```bash
mvn test -DfailIfNoTests=false
```

### Parallel Execution (if configured)
```bash
mvn test -DthreadCount=4
```

### Quiet Mode (Less Output)
```bash
mvn test -q
```

### Debug Mode (Verbose Output)
```bash
mvn test -X
```

---

**Remember**: All commands should be run from the project root directory:
```bash
C:\Users\shubh\eclipse-workspace\API-CI-CD_Integration>
```

---

**Last Updated**: January 15, 2026
