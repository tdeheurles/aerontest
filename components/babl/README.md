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
- works with:
    - [Ui.Demo1](../ui/README.md#uidemo1)
- start the babl server 
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
- works with:
  - [Ui.Demo2](../ui/README.md#uidemo2)
- changelog since [Babl.Demo0](#babldemo0):
  - protocol buffer message with UI
### Code
- [main](./src/main/java/com/tdeheurles/aerontest/babl/Demo1Server.java)
### Run
```shell
RUNNER_DOCKER_ARGS="--shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server_v0" --demo=1
```

## Babl.Demo2
- works with:
  - [Ui.Demo2](../ui/README.md#uidemo2)
- changelog since [Babl.Demo1](#babldemo1):
  - Use babl Additional Work
### Code
- [main](./src/main/java/com/tdeheurles/aerontest/babl/Demo2Server.java)
### Run
```shell
RUNNER_DOCKER_ARGS="--shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server" --demo=2
```

## Babl.Demo3
- works with:
  - [Cluster.Demo2](../Cluster/README.md#clusterdemo2)
  - [Ui.Demo2](../ui/README.md#uidemo2)
  - [messages.demo2](../messages/README.md#messagesdemo2)
- changelog since [Babl.Demo2](#babldemo2):
  - connect to AeronCluster
  - maintain session alive
  - transfer message from UI to Cluster
  - transfer message from Cluster to UI
### Code
- [main](./src/main/java/com/tdeheurles/aerontest/babl/Demo3Server.java)
### Run
```shell
# You have to first run the Cluster.Demo2 
node_ip=10.0.0.5
RUNNER_DOCKER_ARGS="--ip ${node_ip} --network=aeron --shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server_v1" --demo=3 --node_ip="${node_ip}"
```

## Babl.Demo4
- works with:
  - [Cluster.Demo3](../Cluster/README.md#clusterdemo3)
  - [Ui.Demo3](../ui/README.md#uidemo3)
  - [messages.demo3](../messages/README.md#messagesdemo3)
- changelog since [Babl.Demo3](#babldemo3):
  - clean logging
### Code
- [main](./src/main/java/com/tdeheurles/aerontest/babl/Demo4Server.java)
### Run
```shell
# You have to first run the Cluster.Demo3
node_ip=10.0.0.5
RUNNER_DOCKER_ARGS="--ip ${node_ip} --network=aeron --shm-size=512mb -p 8081:8081" \
./do --component="babl" --command="server_v1" --demo=4 --node_ip="${node_ip}"
```
