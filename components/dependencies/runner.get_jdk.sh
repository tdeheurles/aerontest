#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1

  if [[ ! -d ${this_directory}/../binaries/zulu-jdk-15.0.2 ]];then
    ./do --host \
      --component binary --command install \
      --executable zulu-jdk \
      --architecture linux-x64 \
      --version 15.0.2
    fi
)
