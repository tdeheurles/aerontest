# https://github.com/real-logic/aeron/wiki/Cluster-Tutorial#424-consensus-module

CLUSTER_DATA=${CLUSTER_DATA:-"${THIS_DIRECTORY}/cluster_data"}
JAR=${JAR:-"build/libs/Cluster-1.0-SNAPSHOT.jar"}
BASE_CLASS_PATH="com.tdeheurles.aerontest.cluster.Demo"

NODE_0=10.0.0.2
NODE_1=10.0.0.3
NODE_2=10.0.0.4

PORT_ARCHIVE=8000
PORT_INGRESS=8001
PORT_CONSENSUS=8002
PORT_LOG=8003
PORT_CATCHUP=8004
PORT_CONTROL=8005

start_clustered_node(){
  member_config=""
  member_config+="0,"
  member_config+="${NODE_0}:${PORT_INGRESS},"  # ingress/client
  member_config+="${NODE_0}:${PORT_CONSENSUS},"  # consensus/members
  member_config+="${NODE_0}:${PORT_LOG},"  # log
  member_config+="${NODE_0}:${PORT_CATCHUP},"  # catchup/transfer
  member_config+="${NODE_0}:${PORT_ARCHIVE}|"  # archive
  member_config+="1,"
  member_config+="${NODE_1}:${PORT_INGRESS},"
  member_config+="${NODE_1}:${PORT_CONSENSUS},"
  member_config+="${NODE_1}:${PORT_LOG},"
  member_config+="${NODE_1}:${PORT_CATCHUP},"
  member_config+="${NODE_1}:${PORT_ARCHIVE}|"
  member_config+="2,"
  member_config+="${NODE_2}:${PORT_INGRESS},"
  member_config+="${NODE_2}:${PORT_CONSENSUS},"
  member_config+="${NODE_2}:${PORT_LOG},"
  member_config+="${NODE_2}:${PORT_CATCHUP},"
  member_config+="${NODE_2}:${PORT_ARCHIVE}|"
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
      -Dcluster.archive.control_request.port="${PORT_ARCHIVE}" \
      -Dcluster.client_facing.port="${PORT_INGRESS}" \
      -Dcluster.member_facing.port="${PORT_CONSENSUS}" \
      -Dcluster.log.port="${PORT_LOG}" \
      -Dcluster.transfer.port="${PORT_CATCHUP}" \
      -Dcluster.log_control.port="${PORT_CONTROL}" \
      -Dcluster.node.ip="${3}" \
      -Dcluster.members.config="${MEMBER_CONFIG}" \
      -Daeron.event.cluster.log=all \
      -Daeron.print.configuration=true \
      --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
    "${BASE_CLASS_PATH}${1}Cluster"
}

start_client(){
  CLUSTER_INGRESS_ENDPOINT=${CLUSTER_INGRESS_ENDPOINT:-"0=localhost:${PORT_INGRESS},1=${PORT_INGRESS}:${PORT_INGRESS},2=${PORT_INGRESS}:${PORT_INGRESS}"}

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
