# babl - demo

## Demo0
- work in with:
    - Ui.Demo1
- Start the babl server 
- log onSessionConnected, onSessionDisconnected and onSessionMessage.
- onSessionMessage answer with a text message.

## Demo1
- work in with:
    - Ui.Demo2
- Start the babl server
- log onSessionConnected, onSessionDisconnected and onSessionMessage.
- onSessionMessage answer with a simple message.
- protocol buffer message with UI

## Demo2
- work in with:
    - Ui.Demo2
- Start the babl server
- log onSessionConnected, onSessionDisconnected and onSessionMessage.
- onSessionMessage answer with a message.
- protocol buffer message with UI
- additional Work

## Demo3
- work in with:
    - Cluster.Demo2
    - Ui.Demo2
- connect to AeronCluster
- maintain session alive
- transfer message from UI to Cluster
- transfer message from Cluster to UI
```shell
node_0=10.0.0.2
node_1=10.0.0.3
node_2=10.0.0.4
node_ip=10.0.0.5
RUNNER_DOCKER_ARGS="--network=aeron --shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server" --id=3 \
  --node_0="${node_0}" --node_1="${node_1}" --node_2="${node_2}" \
  --node_ip="${node_ip}"
```
