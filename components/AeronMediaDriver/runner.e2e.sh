#!/usr/bin/env bash

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
  cd "${project_root}" || exit 1

  ./do --component AeronMediaDriver --command build
  ./do --component AeronMediaDriver --command delete
  ./do --component AeronMediaDriver --command start
)
