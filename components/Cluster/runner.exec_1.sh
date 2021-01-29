#!/usr/bin/env bash
set -euo pipefail

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
NODE_ID=1
(
  cd "${THIS_DIRECTORY}" || exit 1
  . ./exec_common.sh
  prepare
  start_clustered_node \
    "${NODE_ID}" \
    "8${NODE_ID}00" \
    "8${NODE_ID}01" \
    "8${NODE_ID}02" \
    "8${NODE_ID}03" \
    "8${NODE_ID}04" \
    "8${NODE_ID}05" \
    "${MEMBER_CONFIG}" \
    > "${THIS_DIRECTORY}/cluster_data/${NODE_ID}/node.log" 2>&1
)
