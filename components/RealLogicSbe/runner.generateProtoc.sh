#!/usr/bin/env bash
set -euo pipefail

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

(
  cd "${THIS_DIRECTORY}" || exit 1
  protoc \
    --java_out=./src/main/java/com/tdeheurles/aerontest/reallogicsbe \
    ./src/main/resources/DemoClass.proto
)
