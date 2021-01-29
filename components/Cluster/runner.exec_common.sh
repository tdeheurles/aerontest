# https://github.com/real-logic/aeron/wiki/Cluster-Tutorial#424-consensus-module
MEMBER_CONFIG=""
MEMBER_CONFIG+="0,"
MEMBER_CONFIG+="localhost:8001,"  # ingress/client
MEMBER_CONFIG+="localhost:8002,"  # consensus/members
MEMBER_CONFIG+="localhost:8003,"  # log
MEMBER_CONFIG+="localhost:8004,"  # catchup/transfer
MEMBER_CONFIG+="localhost:8000|"  # archive
MEMBER_CONFIG+="1,"
MEMBER_CONFIG+="localhost:8101,"
MEMBER_CONFIG+="localhost:8102,"
MEMBER_CONFIG+="localhost:8103,"
MEMBER_CONFIG+="localhost:8104,"
MEMBER_CONFIG+="localhost:8100|"
MEMBER_CONFIG+="2,"
MEMBER_CONFIG+="localhost:8201,"
MEMBER_CONFIG+="localhost:8202,"
MEMBER_CONFIG+="localhost:8203,"
MEMBER_CONFIG+="localhost:8204,"
MEMBER_CONFIG+="localhost:8200|"

CLUSTER_DATA="${THIS_DIRECTORY}/cluster_data"

start_clustered_node(){
  java \
    -cp build/libs/Cluster-1.0-SNAPSHOT.jar \
    -javaagent:/home/tdeheurles/repositories/others/aeron/aeron-agent/build/libs/aeron-agent-1.32.1-SNAPSHOT.jar \
      -XX:+UseBiasedLocking \
      -XX:BiasedLockingStartupDelay=0 \
      -XX:+UnlockExperimentalVMOptions \
      -XX:+TrustFinalNonStaticFields \
      -XX:+UnlockDiagnosticVMOptions \
      -XX:GuaranteedSafepointInterval=300000 \
      -XX:+UseParallelOldGC \
      -Dcluster.node.id="${1}" \
      -Dcluster.archive.control_request.port="${2}" \
      -Dcluster.client_facing.port="${3}" \
      -Dcluster.member_facing.port="${4}" \
      -Dcluster.log.port="${5}" \
      -Dcluster.transfer.port="${6}" \
      -Dcluster.log_control.port="${7}" \
      -Dcluster.members.config="${8}" \
      -Daeron.event.cluster.log=all \
      --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
    com.tdeheurles.aerontest.cluster.Start
}

prepare(){
  mkdir -p "${CLUSTER_DATA}/${NODE_ID}"
}
