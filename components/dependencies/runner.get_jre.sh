#!/usr/bin/env bash
set -euo pipefail

architecture="<% architecture %>"
jre_version="<% jre_version %>"
zulu_jre_home="<% zulu_jre_home %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1

  if [[ ! -d "${project_root}/${zulu_jre_home}" ]];then
    ./do --host \
      --component="binary" --command="install" \
      --executable="zulu-jre" \
      --architecture="${architecture}" \
      --version="${jre_version}"
    fi
)
