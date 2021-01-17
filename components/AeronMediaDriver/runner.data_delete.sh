#!/usr/bin/env bash
set -euo pipefail

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

(
  cd "${THIS_DIRECTORY}" || exit 1
  java \
    -cp build/libs/AeronMediaDriver-1.0-SNAPSHOT.jar \
      com.tdeheurles.aerontest.mediadriver.DataDelete \
      <% instance %>
)
