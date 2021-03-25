# babl - demo

## Prerequisites
- Docker is needed.
- On Windows, it's tested on wsl2

Build the code
```shell
./do --component="babl" --command="build"
```

Create a docker network if not present
```shell
docker network create \
  --subnet 10.0.0.0/16 \
  --opt com.docker.network.driver.mtu=9216 \
  --opt com.docker.network.bridge.enable_icc=true \
  --opt com.docker.network.driver.mtu=9000 \
  aeron
```

## Babl.Demo0
- work in with:
    - [Ui.Demo1](../ui/README.md#UI.Demo1)
- Start the babl server 
- log onSessionConnected, onSessionDisconnected and onSessionMessage.
- onSessionMessage answer with a text message.
### Code
- [main](./src/main/java/com/tdeheurles/aerontest/babl/Demo0Server.java)
### Run
```shell
# cd to root of the repository 
RUNNER_DOCKER_ARGS="--shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server_v0" --demo=0
```

## Babl.Demo1
- work in with:
  - [Ui.Demo2](../ui/README.md#UI.Demo2)
- Start the babl server
- log onSessionConnected, onSessionDisconnected and onSessionMessage.
- onSessionMessage answer with a simple message.
- protocol buffer message with UI
### Code
- [main](./src/main/java/com/tdeheurles/aerontest/babl/Demo1Server.java)
### Run
```shell
RUNNER_DOCKER_ARGS="--shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server_v0" --demo=1
```

## Babl.Demo2
- work in with:
  - [Ui.Demo2](../ui/README.md#UI.Demo2)
- Start the babl server
- log onSessionConnected, onSessionDisconnected and onSessionMessage.
- onSessionMessage answer with a message.
- protocol buffer message with UI
- additional Work
### Code
- [main](./src/main/java/com/tdeheurles/aerontest/babl/Demo2Server.java)
### Run
```shell
RUNNER_DOCKER_ARGS="--shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server" --demo=2
```

## Babl.Demo3
- work in with:
  - [Cluster.Demo2](../Cluster/README.md#Cluster.Demo2)
  - [Ui.Demo2](../ui/README.md#UI.Demo2)
- connect to AeronCluster
- maintain session alive
- transfer message from UI to Cluster
- transfer message from Cluster to UI
### Code
- [main](./src/main/java/com/tdeheurles/aerontest/babl/Demo3Server.java)
### Run
```shell
node_ip=10.0.0.5
RUNNER_DOCKER_ARGS="--ip ${node_ip} --network=aeron --shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server_v1" --demo=3 --node_ip="${node_ip}"
```
