#!/usr/bin/env bash
set -euo pipefail

node_bin="<% node_bin %>"
npm="<% npm %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
node_modules_bin="${this_directory}/node_modules/.bin"
pbjs="${node_modules_bin}/pbjs"
pbts="${node_modules_bin}/pbts"
generated="${this_directory}/generated"
js_file="${generated}/message.js"
ts_file="${generated}/message.d.ts"
bundle_file="${generated}/bundle.json"

(
  cd "${project_root}" || exit 1
  ./do --host --component=dependencies --command=get_node
  PATH="${PATH}:${project_root}/${node_bin}"

  cd "${this_directory}" || exit 1
  "${npm}" install

  [[ -e "${generated}" ]] && rm --recursive --force "${generated}"
  mkdir --parents "${generated}"
  "${pbjs}" \
    --target static-module \
    --wrap commonjs \
    --out "${js_file}" \
    "${this_directory}"/../messages/src/*.proto

  "${pbts}" \
    --out "${ts_file}" \
    "${js_file}"

  echo -e "import { Long } from 'protobufjs';\n$(cat "${ts_file}")" \
    > "${ts_file}"

  "${pbjs}" \
    --target json \
    "${this_directory}"/../messages/src/*.proto \
    > "${bundle_file}"

  cat<<EOF > "${generated}/root.ts"
import * as protobuf from 'protobufjs';
const root = protobuf.Root.fromJSON(require('${bundle_file}'));
export default root;
EOF
)
