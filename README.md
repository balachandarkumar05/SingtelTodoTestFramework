# Web-UI Test Automation Project to test Todo Application

Steps to execute Test

1. Clone the repository from link - https://github.com/balachandarkumar05/SingtelTodoTestFramework
2. Build project - "mvn clean install"
3. Feature files directory: src/test/resources/features
4. Runner Class directory: src/test/java
5. Please update tags in CucumberRunner via IDE/commandline to execute specific features/scenarios
6. Please place werdriver.exe under directory - src/test/resources/webdrivers
7. Run test in IDE/mvn command
   1. IDE:
      1. Add new TestNG run configuration and update class parameter as "com.singtel.todo.CucumberRunner"
      2. Once added, select the configuration and click on Run button
   2. Command Line:
      1. mvn clean install test -Dtest="com.singtel.todo.CucumberRunner"
8. After each execution, report will be generated in testreports folder. Please open "overview-features.html" to see summary of execution
