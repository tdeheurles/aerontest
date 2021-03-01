#!/usr/bin/env bash
set -euo pipefail

zulu_jre_java="<% zulu_jre_java %>"
id="<% id %>"
demo="<% demo %>"

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${THIS_DIRECTORY}/../.."
(
  "${project_root}"/do --host --component dependencies --command get_jre
  . "${THIS_DIRECTORY}"/exec_common.sh

  JAVA="${project_root}/${zulu_jre_java}"

  cd "${THIS_DIRECTORY}" || exit 1
  start_client \
      "${demo}" \
      "-Dclient.id=${id}"
)
