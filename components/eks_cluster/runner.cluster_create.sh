#!/usr/bin/env bash
set -euo pipefail

eksctl="<% eksctl %>"
cluster_name="<% cluster_name %>"
aws_region="<% aws_region %>"

subtitle() {
  echo -e "\e[95m${1}\e[39m"
}

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1
  ./do --host --component="dependencies" --command="get_eksctl"

  clusters=$(
    aws eks list-clusters --region="${aws_region}" |
      jq -r ".clusters[]"
  )
  cluster_exists="false"
  for cluster in ${clusters}
  do
    if [[ "${cluster}" == "${cluster_name}" ]];then
      cluster_exists="true"
    fi
  done

  if [[ "${cluster_exists}" == "true" ]];then
    subtitle "Cluster already exists"
  else
    subtitle "Creating Cluster"
    "${eksctl}" create cluster -f "${this_directory}/eksctl/${cluster_name}.yaml"
  fi
  subtitle "\nSet OIDC provider (to bind role to service account)"
  "${eksctl}" utils associate-iam-oidc-provider --cluster "${cluster_name}" --approve

  echo -e "\nCreate context in kubeconfig"
  ./do --host --component="k8s_auth" --command="create_kubeconfig" --cluster_name="${cluster_name}"

  if [[ ! -e "${this_directory}/fluent_bit/${cluster_name}.yaml" ]]; then
    subtitle "\nGenerate logging configuration"
    ./do --host --component="eks_cluster" --command="fluent_bit_get_conf" --cluster_name="${cluster_name}"
  fi

  subtitle "\nSet logging"
  ./do --host --component="eks_cluster" --command="fluent_bit_set_conf" --cluster_name="${cluster_name}"
)
