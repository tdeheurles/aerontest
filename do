#!/bin/bash

# SET VARIABLES
THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

export RUNNER_CHANNEL_OR_VERSION="${RUNNER_CHANNEL_OR_VERSION:=1.4.1.ea361776}"
export RUNNER_HOME="${RUNNER_HOME:=${HOME}/.runner}"
export RUNNER_COMPONENTS_DIR="${RUNNER_COMPONENTS_DIR:=${THIS_DIRECTORY}/components}"
if [[ -n ${RUNNER_EXEC_DIR+x} ]];then
    export RUNNER_DEBUG=True
fi
export RUNNER_EXEC_DIR="${RUNNER_EXEC_DIR:=${RUNNER_HOME}/${RUNNER_CHANNEL_OR_VERSION}}"
export RUNNER_ARTIFACT_URL="${artifact_url:=https://runnerartifacts.s3.eu-west-3.amazonaws.com}"
export RUNNER_DOTENV_DIR="${RUNNER_DOTENV:=$(pwd)}"
export RUNNER_KUBECONFIG="${RUNNER_KUBECONFIG:=${THIS_DIRECTORY}/do.kubeconfig}"
export RUNNER_DO_SCRIPT_VERSION=0

bootstrap_file="${RUNNER_EXEC_DIR}/bootstrap.sh"


# ASSERT DEPENDENCIES
if [[ ! -e "${bootstrap_file}" && "$(which curl)" == "" ]]; then
    echo "curl is a dependency to the runner"
    exit 1
fi


# DOWNLOAD BOOTSTRAP
if [[ ! -e ${bootstrap_file} || $1 == "-u" || $1 == "--update" ]]; then
    mkdir -p "${RUNNER_EXEC_DIR}"
    curl -s -L -o "${bootstrap_file}" \
        ${artifact_url}/${RUNNER_CHANNEL_OR_VERSION}/bootstrap.sh
fi


# IMPORT AND RUN BOOTSTRAP
# shellcheck source=components/runner/bootstrap.sh
. "${bootstrap_file}"

main $@
