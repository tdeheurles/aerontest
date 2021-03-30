#!/usr/bin/env bash
set -euo pipefail

eksctl="<% eksctl %>"
cluster_name="<% cluster_name %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
    cd "${project_root}" || exit 1
    ./do --host --component="dependencies" --command="get_eksctl"

    "${eksctl}" delete cluster --name "${cluster_name}"
)
