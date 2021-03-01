#!/usr/bin/env bash
set -euo pipefail

zulu_jre_java="<% zulu_jre_java %>"

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${THIS_DIRECTORY}/../.."
(
  cd "${project_root}" || exit 1
  ./do --host --component dependencies --command get_jre

  source "${project_root}"/components/Cluster/exec_common.sh

  cd "${THIS_DIRECTORY}" || exit 1
  "${project_root}/${zulu_jre_java}" \
    --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
    --add-opens jdk.unsupported/sun.misc=ALL-UNNAMED \
    -cp build/libs/babl-1.0-SNAPSHOT.jar \
    com.tdeheurles.aerontest.babl.Demo<% id %>Server \
      "${THIS_DIRECTORY}/src/main/resources/demo2.config.properties"
)
