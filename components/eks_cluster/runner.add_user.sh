#!/usr/bin/env bash
set -euo pipefail

#  ./do --component="eks_cluster" --command="adduser" --cluster="dev" --user_arn="arn:aws:iam::898079319887:role/username" --k8s_group="system:masters" --k8s_username="serviceaccountname"

eksctl="<% eksctl %>"
cluster_name="<% cluster_name %>"
user_arn="<% user_arn %>"
k8s_group="<% k8s_group %>"
k8s_username="<% k8s_username %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
    cd "${project_root}" || exit 1
    ./do --host --component="dependencies" --command="get_eksctl"

    "${eksctl}" create iamidentitymapping \
        --cluster "${cluster_name}" \
        --arn "${user_arn}" \
        --group "${k8s_group}" \
        --username "${k8s_username}"
)