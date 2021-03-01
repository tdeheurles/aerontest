#!/usr/bin/env bash
set -euo pipefail

service_id="<% service_id %>"
zulu_jre_java="<% zulu_jre_java %>"

node_id=0

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${THIS_DIRECTORY}/../.."
(
  "${project_root}"/do --host --component dependencies --command get_jre
  . "${THIS_DIRECTORY}"/exec_common.sh
  JAVA="${project_root}/${zulu_jre_java}"

  cd "${THIS_DIRECTORY}" || exit 1
  mkdir -p "${CLUSTER_DATA}/${node_id}"
  start_clustered_node \
    "${service_id}" \
    "${node_id}" \
    "8${node_id}00" \
    "8${node_id}01" \
    "8${node_id}02" \
    "8${node_id}03" \
    "8${node_id}04" \
    "8${node_id}05"
)
