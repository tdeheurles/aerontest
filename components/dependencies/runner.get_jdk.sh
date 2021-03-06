#!/usr/bin/env bash
set -euo pipefail

architecture="<% architecture %>"
jdk_version="<% jdk_version %>"
zulu_jdk_home="<% zulu_jdk_home %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1

  if [[ ! -d "${project_root}/${zulu_jdk_home}" ]];then
    ./do --host \
      --component="binary" --command="install" \
      --executable="zulu-jdk" \
      --architecture="${architecture}" \
      --version="${jdk_version}"
    fi
)
