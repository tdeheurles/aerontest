#!/usr/bin/env bash
set -euo pipefail

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
ID="<% id %>"
DEMO="<% demo %>"
(
  cd "${THIS_DIRECTORY}" || exit 1

  . ./exec_common.sh

  start_client \
      "${DEMO}" \
      "-Dclient.id=${ID}"
)
