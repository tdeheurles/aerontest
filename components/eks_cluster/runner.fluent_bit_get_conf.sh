#!/usr/bin/env bash
set -euo pipefail

cluster_name="<% cluster_name %>"
aws_region="<% aws_region %>"
this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
  cd "${this_directory}" || exit 1
  LogRegion="${aws_region}"
  FluentBitHttpPort='2020'
  FluentBitReadFromHead='Off'
  [[ ${FluentBitReadFromHead} == 'On' ]] && FluentBitReadFromTail='Off' || FluentBitReadFromTail='On'
  [[ -z ${FluentBitHttpPort} ]] && FluentBitHttpServer='Off' || FluentBitHttpServer='On'

  fluent_bit_directory="${this_directory}/fluent_bit"
  mkdir -p "${fluent_bit_directory}"
  curl https://raw.githubusercontent.com/aws-samples/amazon-cloudwatch-container-insights/latest/k8s-deployment-manifest-templates/deployment-mode/daemonset/container-insights-monitoring/quickstart/cwagent-fluent-bit-quickstart.yaml |
    sed "s/{{cluster_name}}/${cluster_name}/;s/{{region_name}}/${LogRegion}/;s/{{http_server_toggle}}/${FluentBitHttpServer}/;s/{{http_server_port}}/${FluentBitHttpPort}/;s/{{read_from_head}}/${FluentBitReadFromHead}/;s/{{read_from_tail}}/${FluentBitReadFromTail}/" \
      >"${fluent_bit_directory}/${cluster_name}.yaml"
)
