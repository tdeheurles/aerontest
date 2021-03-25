#!/usr/bin/env bash
set -euo pipefail

zulu_jre_java="<% zulu_jre_java %>"
node_ip="<% node_ip %>"
demo="<% demo %>"
THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${THIS_DIRECTORY}/../.."
(
  cd "${project_root}" || exit 1
  ./do --host --component="dependencies" --command="get_jre"

  cd "${THIS_DIRECTORY}" || exit 1
  . ../Cluster/exec_common.sh

  ping -c 1 "${NODE_0}"
  ping -c 1 "${NODE_1}"
  ping -c 1 "${NODE_2}"

  "${project_root}/${zulu_jre_java}" \
    --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
    --add-opens jdk.unsupported/sun.misc=ALL-UNNAMED \
    -cp build/libs/babl-1.0-SNAPSHOT.jar \
    "com.tdeheurles.aerontest.babl.Demo${demo}Server" \
      "${THIS_DIRECTORY}/src/main/resources/demo${demo}.config.properties" \
      "0=${NODE_0}:${PORT_INGRESS},1=${NODE_1}:${PORT_INGRESS},2=${NODE_2}:${PORT_INGRESS}" \
      "${node_ip}"
)
