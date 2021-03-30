#!/usr/bin/env bash
set -euo pipefail

eksctl_version="<% eksctl_version %>"
architecture="<% architecture %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1
  if [[ ! -e "${this_directory}/../binaries/eksctl-${eksctl_version}" ]]; then
    ./do --host \
      --component="binary" --command="install" \
      --architecture="${architecture}" \
      --executable="eksctl" \
      --version="${eksctl_version}"
  fi
)
