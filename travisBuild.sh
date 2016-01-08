#!/bin/bash
# Build all Custom Elements
set -e
#./gradlew phantomJsTest --info
#./gradlew chromeTest --info
#./gradlew test
./gradlew chromeTest -Pprofile=PROD
./gradlew safariTest -Pprofile=PROD
./gradlew firefoxTest -Pprofile=PROD
#./gradlew ieTest -Pprofile=PROD