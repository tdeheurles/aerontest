#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
    cd "${this_directory}" || exit 1
    ./use_context.sh

    namespace="amazon-cloudwatch"
    pod_id=$(
        kubectl get pod --namespace "${namespace}" -o name --no-headers=true | \
            grep <% daemon %>
    )

    kubectl logs ${pod_id} --namespace "${namespace}" | more
)
