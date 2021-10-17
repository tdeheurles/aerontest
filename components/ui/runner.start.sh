#!/usr/bin/env bash

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
  cd "${this_directory}/demo<% id %>" || exit 1
  (
    export PATH+=":../../binaries/node-14.15.5/bin"
    npm start
  )
)
