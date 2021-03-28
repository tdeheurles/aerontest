#!/usr/bin/env bash
set -euo pipefail

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1
  proto_path=${this_directory}/src
  generated="${this_directory}/generated"
  java_generated_path="${generated}/java"
  javascript_generated_path="${generated}/javascript"
  ./do --host --component="dependencies" --command="get_node"
  export PATH="${PATH}:${project_root}/${NODE_BIN}"

  cd "${this_directory}" || exit 1
  npm install \
    ts-protoc-gen \
    @improbable-eng/grpc-web \
    @types/google-protobuf \
    google-protobuf

  [[ -e "${generated}" ]] && rm -r "${generated}"
  mkdir --parents \
    "${java_generated_path}" \
    "${javascript_generated_path}"

  protoc \
    --proto_path="${proto_path}" \
    --plugin="protoc-gen-ts=${this_directory}/node_modules/.bin/protoc-gen-ts" \
    --java_out="${java_generated_path}" \
    --js_out=library="demo,binary:${javascript_generated_path}" \
    --ts_out="service=grpc-web:${javascript_generated_path}" \
    ${proto_path}/*.proto

    cd "${project_root}" || exit 1
    ./do --host --component="ui" --command="generate"
)
