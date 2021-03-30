#!/usr/bin/env bash
set -euo pipefail

build_pipeline=${1}
git_branch=${2}
git_sha=${3}
docker_registry=${4}
semantic_versioning=${5}
docker_username=${6}
docker_password=${7}

build_number=${git_sha:0:8}
git_short_sha=${git_sha:0:8}

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."

mkdir -p /kaniko/.docker
echo "{\"auths\":{\"$docker_registry\":{\"username\":\"$docker_username\",\"password\":\"$docker_password\"}}}" >/kaniko/.docker/config.json

cd "${project_root}" || exit 1
./do --host --component="dependencies" --command="get_node"
./do --host --component="dependencies" --command="get_jdk"
./do --host --component="dependencies" --command="get_jre"

echo -e "\e[36m------------ Set variables ------------\e[39m"
image_id="${semantic_versioning}.${build_number}"
destination_1=${docker_registry}:${image_id}
destination_2=${docker_registry}:${semantic_versioning}
destination_3=${docker_registry}:${build_pipeline}
destination_4=${docker_registry}:${git_branch}
destination_5=${docker_registry}:${git_short_sha}
command="/kaniko/executor"
command="${command} --cache=true"
command="${command} --cache-repo ${docker_registry}"
command="${command} --context ${project_root}"
command="${command} --dockerfile ${this_directory}/Dockerfile"
command="${command} --destination ${destination_1}"
command="${command} --destination ${destination_2}"
command="${command} --destination ${destination_3}"
if [ -n "${git_branch##*/*}" ]; then
  command="${command} --destination ${destination_4}"
fi
command="${command} --destination ${destination_5}"

echo -e "\e[36m------------ Variables dump ------------\e[39m"
echo "command: ${command}"

echo -e "\e[36m------------ /kaniko/executor ------------\e[39m"
${command}
