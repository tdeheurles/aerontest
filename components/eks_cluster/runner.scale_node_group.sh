#!/usr/bin/env bash
set -euo pipefail

cluster_name="<% cluster_name %>"
eksctl="<% eksctl %>"
group_name="<% group_name %>"
desired_count="<% desired_count %>"
minimum_size="<% minimum_size %>"
maximum_size="<% maximum_size %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
    cd "${project_root}" || exit 1
    ./do --host --component="dependencies" --command="get_eksctl"

    "${eksctl}" scale nodegroup \
        --cluster="${cluster_name}" \
        --nodes="${desired_count}" \
        --name="${group_name}" \
        --nodes-min="${minimum_size}" \
        --nodes-max="${maximum_size}"
)
