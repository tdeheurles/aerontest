#!/usr/bin/env bash

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

(
  cd "${THIS_DIRECTORY}" || exit 1

  ./build.sh
  ./data_delete.sh
  ./start.sh
)
