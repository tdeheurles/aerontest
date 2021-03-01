#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1
  ./do --host --component dependencies --command get_jdk

  cd "${this_directory}" || exit 1
  binaries="${this_directory}/../binaries"
  JAVA_HOME="${binaries}/zulu-jdk-15.0.2"
  PATH="${PATH}:${JAVA_HOME}/bin"

  export GRADLE_USER_HOME="${binaries}/gradle-cache"
#  ./gradlew clean
  ./gradlew build
  ./gradlew jar
)
