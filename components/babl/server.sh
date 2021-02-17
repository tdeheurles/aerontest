#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
  cd "${this_directory}" || exit 1

  ./get_exec_dependencies.sh
  ../binaries/zulu-jdk-15.0.2/bin/java \
    -cp build/libs/babl-1.0-SNAPSHOT.jar \
    com.tdeheurles.aerontest.babl.Demo0Server
)
