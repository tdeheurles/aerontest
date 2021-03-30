#!/usr/bin/env bash
set -euo pipefail

cluster_name="<% cluster_name %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1
  ./do --host --component="k8s_auth" --command="use_context" --cluster_name="${cluster_name}"

  cd "${this_directory}" || exit 1
  kubectl apply -f "${this_directory}/fluent_bit/${cluster_name}.yaml"
)
