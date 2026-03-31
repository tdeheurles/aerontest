#!/usr/bin/env bash
set -euo pipefail

aeron_agent_version="<% aeron_agent_version %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
download_directory="${this_directory}/../binaries/jar"
download_file="${download_directory}/aeron-agent-${aeron_agent_version}-SNAPSHOT.jar"
(
  cd "${project_root}" || exit 1
  if [[ ! -e "${download_file}" ]]; then
    mkdir -p "${download_directory}"
    curl -fL \
      -o "${download_file}" \
      https://repo.maven.apache.org/maven2/io/aeron/aeron-agent/${aeron_agent_version}/aeron-agent-${aeron_agent_version}.jar
  fi
)
