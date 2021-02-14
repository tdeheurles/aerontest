#!/usr/bin/env bash

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
  cd "${this_directory}" || exit 1
  yarn start
)
