# https://github.com/real-logic/aeron/wiki/Cluster-Tutorial#424-consensus-module

CLUSTER_DATA=${CLUSTER_DATA:-"${THIS_DIRECTORY}/cluster_data"}
JAR=${JAR:-"build/libs/Cluster-1.0-SNAPSHOT.jar"}
BASE_CLASS_PATH="com.tdeheurles.aerontest.cluster.Demo"

NODE_0="<% node_0 %>"
NODE_1="<% node_1 %>"
NODE_2="<% node_2 %>"

start_clustered_node(){
  member_config=""
  member_config+="0,"
  member_config+="${NODE_0}:8001,"  # ingress/client
  member_config+="${NODE_0}:8002,"  # consensus/members
  member_config+="${NODE_0}:8003,"  # log
  member_config+="${NODE_0}:8004,"  # catchup/transfer
  member_config+="${NODE_0}:8000|"  # archive
  member_config+="1,"
  member_config+="${NODE_1}:8001,"
  member_config+="${NODE_1}:8002,"
  member_config+="${NODE_1}:8003,"
  member_config+="${NODE_1}:8004,"
  member_config+="${NODE_1}:8000|"
  member_config+="2,"
  member_config+="${NODE_2}:8001,"
  member_config+="${NODE_2}:8002,"
  member_config+="${NODE_2}:8003,"
  member_config+="${NODE_2}:8004,"
  member_config+="${NODE_2}:8000|"
  MEMBER_CONFIG=${MEMBER_CONFIG:-${member_config}}

  # Large Timeout are present for debug purposes
  "${JAVA}" \
    -cp "${JAR}" \
    -javaagent:"${THIS_DIRECTORY}"/../binaries/jar/aeron-agent-1.32.1-SNAPSHOT.jar \
      -Daeron.driver.timeout=60000000000 \
      -Daeron.cluster.session.timeout=60000000000 \
      -Daeron.client.liveness.timeout=60000000000 \
      -Daeron.rcv.status.message.timeout=60000000000 \
      -Daeron.client.liveness.timeout=60000000000 \
      -Daeron.image.liveness.timeout=60000000000 \
      -Daeron.publication.unblock.timeout=120000000000 \
      -Daeron.publication.connection.timeout=60000000000 \
      -Daeron.untethered.window.limit.timeout=60000000000 \
      -Daeron.untethered.resting.timeout=60000000000 \
      -Daeron.flow.control.receiver.timeout=60000000000 \
      -XX:+UseBiasedLocking \
      -XX:BiasedLockingStartupDelay=0 \
      -XX:+UnlockExperimentalVMOptions \
      -XX:+TrustFinalNonStaticFields \
      -XX:+UnlockDiagnosticVMOptions \
      -XX:GuaranteedSafepointInterval=300000 \
      -XX:+UseParallelOldGC \
      -Dcluster.node.id="${2}" \
      -Dcluster.archive.control_request.port="${3}" \
      -Dcluster.client_facing.port="${4}" \
      -Dcluster.member_facing.port="${5}" \
      -Dcluster.log.port="${6}" \
      -Dcluster.transfer.port="${7}" \
      -Dcluster.log_control.port="${8}" \
      -Dcluster.node.ip="${9}" \
      -Dcluster.members.config="${MEMBER_CONFIG}" \
      -Daeron.event.cluster.log=all \
      -Daeron.print.configuration=true \
      --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
    "${BASE_CLASS_PATH}${1}Cluster"
}

start_client(){
  CLUSTER_INGRESS_ENDPOINT=${CLUSTER_INGRESS_ENDPOINT:-"0=localhost:8001,1=localhost:8101,2=localhost:8201"}

  "${JAVA}" \
    -cp "${JAR}" \
      -XX:+UseBiasedLocking \
      -XX:BiasedLockingStartupDelay=0 \
      -XX:+UnlockExperimentalVMOptions \
      -XX:+TrustFinalNonStaticFields \
      -XX:+UnlockDiagnosticVMOptions \
      -XX:GuaranteedSafepointInterval=300000 \
      -XX:+UseParallelOldGC \
      -Daeron.event.cluster.log=all \
      -Dcluster.ingress.endpoints="${CLUSTER_INGRESS_ENDPOINT}" \
      "${2}" \
      --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
    "${BASE_CLASS_PATH}${1}EgressListener"
}
