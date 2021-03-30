#!/usr/bin/env bash
set -euo pipefail

cluster_name="<% cluster_name %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
    cd "${this_directory}" || exit 1
    kubectl config use-context "${cluster_name}" \
        > /dev/nul
)
