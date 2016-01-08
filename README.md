# Description

This repository have example to write Functional Test in Geb/Groovy/Spock/Junit Test cases. This is enterprise Ready Project where you can set your profile for DEV/QA/staging/Prod Environment. you can run test case on your local machine as well as Travis CI tool. 

You will need JAVA, gradle and browser setup for same.
The build is setup to work with Firefox, Chrome, IE, Safari and PhantomJS. Have a look at the `build.gradle` and the `src/test/resources/GebConfig.groovy` files.

Reference

    http://www.gebish.org/manual/current/
    https://github.com/geb/geb-example-gradle.git

# Usage

The following commands will launch the tests with the individual browsers:
    
    ./gradlew chromeTest
    ./gradlew firefoxTest
    ./gradlew phantomJsTest
    ./gradlew ieTest
    ./gradlew safariTest

To run with all, you can run:

    ./gradlew test

Run on local machine
===============

Right now test cases are configured to run on SauceLab if you run by default.
If you want to run on your local machine change below setting

1. open build.gradle file

```
def automationCredentials = [
        username: "",
        apiKey: ""
]
```



Run on windows
===============

Replace `./gradlew` with `gradlew.bat` in the above examples if you're on Windows.

Run on safari Prerequisite
===============

1. download web driver by following instructions : http://elementalselenium.com/tips/69-safari
2. Instructions - http://selenium-release.storage.googleapis.com/index.html?path=2.48/    -download SafariDriver.safariextz


Run test cases particuler to enviorment
===============

    ./gradlew chromeTest -Pprofile=QA
    ./gradlew firefoxTest -Pprofile=PROD
    ./gradlew ieTest -Pprofile=PERF

To update test cases for Travis
===============

look for travisBuild.sh and change task
