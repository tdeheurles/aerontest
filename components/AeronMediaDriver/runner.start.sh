#!/usr/bin/env bash
set -euo pipefail

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

(
  cd "${THIS_DIRECTORY}" || exit 1
  java \
    --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
    --add-opens jdk.unsupported/sun.misc=ALL-UNNAMED \
    -cp build/libs/AeronMediaDriver-1.0-SNAPSHOT.jar \
      com.tdeheurles.aerontest.mediadriver.Start \
      <% instance %>
)
