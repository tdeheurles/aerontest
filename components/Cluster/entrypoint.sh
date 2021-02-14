#!/usr/bin/env bash
set -euo pipefail

NODE_ID=${NODE_ID:?$(echo "missing ENV variable NODE_ID" && exit 1)}
SERVICE_ID=${SERVICE_ID:?$(echo "missing ENV variable SERVICE_ID" && exit 1)}

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
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
