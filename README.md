# StreamTvTests

How to setup & launch the automation framework

Please clone this repository to your local machine with installed software :
- Maven (version 3.1.1 and newer)
- Java 7 and high
- Firefox

To run tests please run in project folder command : *mvn test*.

Script part. Script is located in script folder in project. 
Steps to run script : 
- Please put some files in Weekend and BusinessDays folders before running script.
- in Linux console execute the following in the script's folder : _chmod +x ./script.sh_
- execute in the script's folder run : *./script.sh*.

Allure reports. After test run you can find report files under /target/allure-results folder.
To generate html reports execute _mvn site_, after that result can be found under /target/site/allure-maven-plugin folder.
Please follow official Allure documentation to open html reports
