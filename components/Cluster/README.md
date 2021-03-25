# Cluster - Demo

## Prerequisites
- Docker is needed.
- On Windows, it's tested on wsl2 

Build the code
```shell
./do --component="Cluster" --command="gradle_build"
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

## Cluster.Demo0
- ClusteredService
    - print a log of each event type

### Code
- [main](./src/main/java/com/tdeheurles/aerontest/cluster/Demo0Cluster.java)

### Run
#### Start the cluster
On 3 different terminals, execute:
```shell
node_ip=10.0.0.2
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_ip} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node_v0" \
     --demo=0 --node_id=0 --node_ip="${node_ip}"
```
```shell
node_ip=10.0.0.3
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_ip} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node_v0" \
     --demo=0 --node_id=1 --node_ip="${node_ip}"
```
```shell
node_ip=10.0.0.4
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_ip} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node_v0" \
     --demo=0 --node_id=2 --node_ip="${node_ip}"
```


## Cluster.Demo1
- ClusteredService
    - log of each event type 
    - log message from EgressListener
- EgressListener
    - print a log of each event type
    - send a message to ClusteredService

### Code
- [main](./src/main/java/com/tdeheurles/aerontest/cluster/Demo1Cluster.java)

### Run
#### Start the cluster
On 3 different terminal, execute:
```shell
node_ip=10.0.0.2
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_ip} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node_v0" \
     --demo=1 --node_id=0 --node_ip="${node_ip}"
```
```shell
node_ip=10.0.0.3
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_ip} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node_v0" \
     --demo=1 --node_id=1 --node_ip="${node_ip}"
```
```shell
node_ip=10.0.0.4
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_ip} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node_v0" \
     --demo=1 --node_id=2 --node_ip="${node_ip}"
```
#### Send a message
On a 4th terminal, execute:
```shell
./do --host --component="Cluster" --command="exec_client" --demo=1
```

## Cluster.Demo2
- Works with:
    - [Babl.Demo3](../babl/README.md#Babl.Demo3)
- ClusteredService
    - log of each event type
    - log message from EgressListener
### Code
- [main](./src/main/java/com/tdeheurles/aerontest/cluster/Demo2Cluster.java)
### Run
On 3 different terminal, execute:
```shell
node_ip=10.0.0.2
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_ip} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node_v0" \
     --demo=2 --node_id=0 --node_ip="${node_ip}"
```
```shell
node_ip=10.0.0.3
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_ip} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node_v0" \
     --demo=2 --node_id=1 --node_ip="${node_ip}"
```
```shell
node_ip=10.0.0.4
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_ip} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node_v0" \
     --demo=2 --node_id=2 --node_ip="${node_ip}"
```
