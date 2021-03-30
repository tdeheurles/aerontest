#!/usr/bin/env bash
set -euo pipefail

eksctl="<% eksctl %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
    cd "${project_root}" || exit 1
    ./do --host --component="dependencies" --command="get_eksctl"

    color_0="\e[35m"
    color_1="\e[36m"
    color_no="\e[39m"

    echo -e "${color_0}-- EKSCTL${color_no}"
    echo -e "${color_1}------ CLUSTER ------ ${color_no}"
    "${eksctl}" get cluster
)
