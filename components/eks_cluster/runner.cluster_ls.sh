#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
(
    cd "${this_directory}" || exit 1

    color_0="\e[35m"
    color_1="\e[36m"
    color_no="\e[39m"

    echo -e "${color_0}-- EKSCTL${color_no}"
    echo -e "${color_1}------ CLUSTER ------ ${color_no}"
    eksctl get cluster
)
