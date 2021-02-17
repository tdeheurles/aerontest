#!/usr/bin/env bash
set -euo pipefail

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
NODE_ID=2
SERVICE_ID="<% service_id %>"

(
  cd "${THIS_DIRECTORY}" || exit 1
  . ./exec_common.sh

  mkdir -p "${CLUSTER_DATA}/${NODE_ID}"
  start_clustered_node \
    "${SERVICE_ID}" \
    "${NODE_ID}" \
    "8${NODE_ID}00" \
    "8${NODE_ID}01" \
    "8${NODE_ID}02" \
    "8${NODE_ID}03" \
    "8${NODE_ID}04" \
    "8${NODE_ID}05"
)
