# babl - demo

## Demo0
- work in with:
    - Ui.Demo1
- Start the babl server 
- log onSessionConnected, onSessionDisconnected and onSessionMessage.
- onSessionMessage answer with a text message.
```shell
RUNNER_DOCKER_ARGS="--shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server_v0" --demo=0
```

## Demo1
- work in with:
    - Ui.Demo2
- Start the babl server
- log onSessionConnected, onSessionDisconnected and onSessionMessage.
- onSessionMessage answer with a simple message.
- protocol buffer message with UI
```shell
RUNNER_DOCKER_ARGS="--shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server_v0" --demo=1
```

## Demo2
- work in with:
    - Ui.Demo2
- Start the babl server
- log onSessionConnected, onSessionDisconnected and onSessionMessage.
- onSessionMessage answer with a message.
- protocol buffer message with UI
- additional Work
```shell
RUNNER_DOCKER_ARGS="--shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server" --demo=2
```

## Demo3
- work in with:
    - Cluster.Demo2
    - Ui.Demo2
- connect to AeronCluster
- maintain session alive
- transfer message from UI to Cluster
- transfer message from Cluster to UI
```shell
node_ip=10.0.0.5
RUNNER_DOCKER_ARGS="--ip ${node_ip} --network=aeron --shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server_v1" --demo=3 --node_ip="${node_ip}"
```
