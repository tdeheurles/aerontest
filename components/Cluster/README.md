# Cluster - Demo

## Build
```shell
./do --host --component="Cluster" --command="gradle_build"
```


## Demo0
- ClusteredService
    - print a log of each event type

### Run
#### Start the cluster
On 3 different terminals, execute:
```shell
./do --host --component="Cluster" --command="exec_node" \
     --service_id=0 --node_id=0
```
```shell
./do --host --component="Cluster" --command="exec_node" \
     --service_id=0 --node_id=1
```
```shell
./do --host --component="Cluster" --command="exec_node" \
     --service_id=0 --node_id=2
```

## Demo1
- ClusteredService
    - log of each event type 
    - log message from EgressListener
- EgressListener
    - print a log of each event type
    - send a message to ClusteredService

### Run
#### Start the cluster
On 3 different terminal, execute:
```shell
./do --host --component="Cluster" --command="exec_node" \
     --service_id=1 --node_id=0
```
```shell
./do --host --component="Cluster" --command="exec_node" \
     --service_id=1 --node_id=1
```
```shell
./do --host --component="Cluster" --command="exec_node" \
     --service_id=1 --node_id=2
```
#### Send a message
On a 4th terminal, execute:
```shell
./do --host --component="Cluster" --command="exec_client" \
     --service_id=1
```
## Demo2
- Works with:
    - Babl.Demo3
- ClusteredService
    - log of each event type
    - log message from EgressListener
### Run
Create a docker network
```shell
docker network create \
  --subnet 10.0.0.0/16 \
  --opt com.docker.network.driver.mtu=9216 \
  --opt com.docker.network.bridge.enable_icc=true \
  --opt com.docker.network.driver.mtu=9000 \
  aeron
```
On 3 different terminal, execute:
```shell
node_0=10.0.0.2
node_1=10.0.0.3
node_2=10.0.0.4
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_0} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node" \
     --service_id=2 --node_id=0 --node_ip="${node_0}" \
     --node_0="${node_0}" --node_1="${node_1}" --node_2="${node_2}"
```
```shell
node_0=10.0.0.2
node_1=10.0.0.3
node_2=10.0.0.4
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_1} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node" \
     --service_id=2 --node_id=1 --node_ip="${node_1}" \
     --node_0="${node_0}" --node_1="${node_1}" --node_2="${node_2}"
```
```shell
node_0=10.0.0.2
node_1=10.0.0.3
node_2=10.0.0.4
RUNNER_DOCKER_ARGS="--network=aeron --ip=${node_2} --shm-size=512Mb" \
./do --component="Cluster" --command="exec_node" \
     --service_id=2 --node_id=2 --node_ip="${node_2}" \
     --node_0="${node_0}" --node_1="${node_1}" --node_2="${node_2}"
```
