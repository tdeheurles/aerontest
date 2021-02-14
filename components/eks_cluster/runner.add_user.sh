#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

(
    cd "${this_directory}" || exit 1
    ./use_context.sh
    eksctl create iamidentitymapping \
        --cluster <% cluster_name %> \
        --arn <% user_arn %> \
        --group <% k8s_group %> \
        --username <% k8s_username %>
)
