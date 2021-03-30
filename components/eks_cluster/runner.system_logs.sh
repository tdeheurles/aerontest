#!/usr/bin/env bash
set -euo pipefail

daemon="<% daemon %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
    cd "${this_directory}" || exit 1
    ./do --host --component="eks_cluster" --command="use_context"

    namespace="amazon-cloudwatch"
    pod_id=$(
        kubectl get pod --namespace "${namespace}" -o name --no-headers=true | \
            grep "${daemon}"
    )

    kubectl logs "${pod_id}" --namespace "${namespace}" | more
)
