#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
    cd "${this_directory}" || exit 1
    eksctl create cluster \
        -f ./eksctl/<% cluster_name %>.yaml

    eksctl utils associate-iam-oidc-provider --cluster <% cluster_name %> --approve
)
