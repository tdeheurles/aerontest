#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
  cd "${this_directory}" || exit 1

  ./get_build_dependencies.sh
  binaries="${this_directory}/../binaries"
  JAVA_HOME="${binaries}/zulu-jdk-15.0.2"
  PATH="${PATH}:${JAVA_HOME}/bin"

  export GRADLE_USER_HOME="${binaries}/gradle-cache"
  ./gradlew clean
  ./gradlew --stacktrace build
  ./gradlew jar
)
