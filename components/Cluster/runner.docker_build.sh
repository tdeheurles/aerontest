#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 &
(
  cd "${this_directory}" || exit 1
  ./
  docker build -t tdeheurles/aerontest:<% version %> .
)
