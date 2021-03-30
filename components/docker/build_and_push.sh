#!/usr/bin/env bash
set -euo pipefail

echo hello

#build_number=${BUILD_NUMBER:?$(echo -e "\e[92mPlease provide environment variable BUILD_NUMBER\e[39m" && exit 1)}       # CI_COMMIT_SHORT_SHA
#build_pipeline=${BUILD_PIPELINE:?$(echo -e "\e[92mPlease provide environment variable BUILD_PIPELINE\e[39m" && exit 1)} # CI_PIPELINE_ID
#git_branch=${GIT_BRANCH:?$(echo -e "\e[92mPlease provide environment variable GIT_BRANCH\e[39m" && exit 1)}             # CI_COMMIT_BRANCH
#git_short_sha=${GIT_SHORT_SHA:?$(echo -e "\e[92mPlease provide environment variable GIT_SHORT_SHA\e[39m" && exit 1)}    # CI_COMMIT_SHORT_SHA
#docker_registry=${DOCKER_REGISTRY:?$(echo -e "\e[92mPlease provide environment variable SERVICE_NAME\e[39m" && exit 1)}
#semantic_versioning=${SEMANTIC_VERSIONING:?$(echo -e "\e[92mPlease provide environment variable SEMANTIC_VERSIONING\e[39m" && exit 1)}
#docker_username=${DOCKER_USERNAME:?$(echo -e "\e[92mPlease provide environment variable DOCKER_USERNAME\e[39m" && exit 1)}
#docker_password=${DOCKER_PASSWORD:?$(echo -e "\e[92mPlease provide environment variable DOCKER_PASSWORD\e[39m" && exit 1)}
#
#this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
#project_root="${this_directory}/../.."
#(
#  mkdir -p /kaniko/.docker
#  echo "{\"auths\":{\"$docker_registry\":{\"username\":\"$docker_username\",\"password\":\"$docker_password\"}}}" >/kaniko/.docker/config.json
#
#  cd "${project_root}" || exit 1
#  ./do --host --component="dependencies" --command="get_node"
#  ./do --host --component="dependencies" --command="get_jdk"
#  ./do --host --component="dependencies" --command="get_jre"
#
#  echo -e "\e[36m------------ Set variables ------------\e[39m"
#  image_id="${semantic_versioning}.${build_number}"
#  destination_1=${docker_registry}:${image_id}
#  destination_2=${docker_registry}:${semantic_versioning}
#  destination_3=${docker_registry}:${build_pipeline}
#  destination_4=${docker_registry}:${git_branch}
#  destination_5=${docker_registry}:${git_short_sha}
#  command="/kaniko/executor"
#  command="${command} --cache=true"
#  command="${command} --cache-repo ${docker_registry}"
#  command="${command} --context ${project_root}"
#  command="${command} --dockerfile ${this_directory}/Dockerfile"
#  command="${command} --destination ${destination_1}"
#  command="${command} --destination ${destination_2}"
#  command="${command} --destination ${destination_3}"
#  if [ -n "${CI_COMMIT_BRANCH##*/*}" ]; then
#    command="${command} --destination ${destination_4}"
#  fi
#  command="${command} --destination ${destination_5}"
#
#  echo -e "\e[36m------------ Variables dump ------------\e[39m"
#  echo "command: ${command}"
#
#  echo -e "\e[36m------------ /kaniko/executor ------------\e[39m"
#  ${command}
#)
