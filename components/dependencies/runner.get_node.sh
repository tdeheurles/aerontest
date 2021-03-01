#!/usr/bin/env bash
set -euo pipefail

node_home="<% node_home %>"
node_version="<% node_version %>"
architecture="<% architecture %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1

  if [[ ! -d "${node_home}" ]];then
    ./do --host \
      --component binary --command install \
      --executable node \
      --architecture "${architecture}" \
      --version "${node_version}"
    fi
)
