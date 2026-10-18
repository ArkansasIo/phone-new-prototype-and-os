#!/usr/bin/env bash
set -euo pipefail

command -v java >/dev/null || { echo 'Java 21+ is required'; exit 1; }
command -v ./gradlew >/dev/null 2>&1 || { echo 'Gradle wrapper is not committed yet; use a Gradle 8.x installation or generate wrapper locally.'; exit 1; }

./gradlew :shared:build :prototype:test :androidApp:assembleDebug
