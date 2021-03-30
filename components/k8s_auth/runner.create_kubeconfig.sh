#!/usr/bin/env bash
set -euo pipefail

cluster_name="<% cluster_name %>"
cluster_name="<% cluster_name %>"
aws_region="<% aws_region %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
kubeconfig="${project_root}/do.kubeconfig"
(
    cd "${project_root}" || exit 1
    aws eks \
        --region "${aws_region}" \
        update-kubeconfig \
            --name "${cluster_name}" \
            --kubeconfig "${kubeconfig}" \
            --alias "${cluster_name}"
)
