#!/usr/bin/env bash
set -euo pipefail

zulu_jre_java="<% zulu_jre_java %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
( 
    cd "${project_root}" || exit 1
    ./do --host --component="dependencies" --command="get_jre"

    JAVA="${project_root}/${zulu_jre_java}"

    cd "${THIS_DIRECTORY}" || exit 1
    ./gradlew clean
    # ./gradlew generateMessages
    ./gradlew build
    ./gradlew jar
)
