#!/usr/bin/env bash
set -euo pipefail

demo="<% demo %>"
zulu_jre_java="<% zulu_jre_java %>"
node_id="<% node_id %>"
node_ip="<% node_ip %>"

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${THIS_DIRECTORY}/../.."
(
  cd "${project_root}" || exit 1
  ./do --host --component="dependencies" --command="get_jre"

  JAVA="${project_root}/${zulu_jre_java}"

  cd "${THIS_DIRECTORY}" || exit 1
  . "${THIS_DIRECTORY}"/exec_common.sh
  mkdir -p "${CLUSTER_DATA}/${node_id}"
  start_clustered_node \
    "${demo}" \
    "${node_id}" \
    "${node_ip}"
)
