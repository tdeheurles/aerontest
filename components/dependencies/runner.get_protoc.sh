#!/usr/bin/env bash
set -euo pipefail

architecture="<% architecture %>"
version="<% protoc_version %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
( 
    cd "${project_root}" || exit 1

    protoc_path="${this_directory}/../binaries/protoc-${version}"
    if [[ ! -d "${protoc_path}" ]]; then
        ./do --host \
            --component="binary" --command="install" \
            --executable="protoc" \
            --architecture="${architecture}" \
            --version="${version}"
    fi
)

#RUN PROTOC_ZIP=protoc-3.14.0-linux-x86_64.zip \
#    && curl -OL https://github.com/protocolbuffers/protobuf/releases/download/v3.14.0/$PROTOC_ZIP \
#    && unzip -o $PROTOC_ZIP -d /usr/local bin/protoc \
#    && unzip -o $PROTOC_ZIP -d /usr/local 'include/*' \
#    && rm -f $PROTOC_ZIP
