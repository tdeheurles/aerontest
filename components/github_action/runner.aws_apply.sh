#!/usr/bin/env bash
set -euo pipefail

project_name="<% project_name %>"
aws_region="<% aws_region %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1

  bucket_name="${project_name}-data"

  ./do --host \
    --component="eks_cluster" \
    --command="create_bucket" \
    --name="${bucket_name}"

  ./do --host \
    --component="github-action" \
    --command="cf_apply" \
    --stack_name="github-action" \
    --bucket_name="${bucket_name}" \
    --aws_region="${aws_region}" \
    --aws_default_region="${aws_region}"
)
