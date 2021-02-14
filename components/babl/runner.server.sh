#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
  cd "${this_directory}" || exit 1
  java \
    -cp build/libs/babl-1.0-SNAPSHOT.jar \
    com.tdeheurles.aerontest.babl.Demo<% id %>Server
)
