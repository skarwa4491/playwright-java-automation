Project Build Instructions

Prerequisites
1. Java Version: OpenJDK 17.0.10 (LTS)
2. Gradle Version: 8.11.1
3. IDE: IntelliJ IDEA (Community Edition or Licensed Version)

Steps to Build and Run the Project
Follow these steps to build and execute the project:
1. Clean the Project
    **./gradlew clean**
2. Build the Project Without Running Tests Build the project and skip the execution of tests
   **./gradlew build -x test**
3. Run Tests with Environment Variable Run tests with a specific environment configuration 
   1. For jarvis environment: **./gradlew test -Denv=jarvis**
   2. For uat environment: **./gradlew test -Denv=uat**