#!/usr/bin/env bash
set -euo pipefail

THIS_DIRECTORY="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

(
  cd "${THIS_DIRECTORY}" || exit 1
  echo
  java \
    -cp build/libs/Cluster-1.0-SNAPSHOT.jar \
      -XX:+UnlockExperimentalVMOptions \
      -XX:+TrustFinalNonStaticFields \
      -XX:+UnlockDiagnosticVMOptions \
      -XX:GuaranteedSafepointInterval=300000 \
        -Dcluster.node.id=<% node_id %> \
        -Dcluster.archive.control_request.port=<% archive_port %> \
        -Dcluster.client_facing.port=<% client_port %> \
        -Dcluster.member_facing.port=<% member_port %> \
        -Dcluster.log.port=<% log_port %> \
        -Dcluster.log_control.port=<% log_control_port %> \
        -Dcluster.transfer.port=<% transfer_port %> \
        -Dcluster.members.config="<% member_config %>" \
      --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
    com.tdeheurles.aerontest.cluster.Start
)
