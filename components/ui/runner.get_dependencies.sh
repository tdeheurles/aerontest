#!/usr/bin/env bash

node_path="<% node_path %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1
  if [[ ! -e "${node_path}" ]]; then
    ./do --host --component=binary --command=install \
      --executable=node --version=14.15.5 \
      --architecture="<% architecture $>"
  fi
)
