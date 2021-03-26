# UI - Demo

## UI.Demo0
- Works with:
    - [Babl.Demo0](../babl/README.md#Babl.Demo0)
- Connect to server and send one simple message
- Out are shown in the console
### Code
- [project root](./demo0)

## UI.Demo1
- works with:
  - [Babl.Demo0](../babl/README.md#Babl.Demo0)
- see https://golb.hplar.ch/2020/04/rxjs-websocket.html
- Angular
- RxJsWebSocket
    - reconnection
### Code
- [project root](./demo1)

## UI.Demo2
- works with:
  - [Babl.Demo0](../babl/README.md#Babl.Demo0)
  - [Babl.Demo1](../babl/README.md#Babl.Demo1)
  - [Babl.Demo2](../babl/README.md#Babl.Demo2)
- see https://golb.hplar.ch/2020/04/rxjs-websocket.html
- Angular
- RxJsWebSocket
    - reconnection
- Protobuf
### Code
- [project root](./demo2)
### Run
```shell
RUNNER_DOCKER_ARGS="--network=aeron -p 4200:4200" \
./do --component="ui" --command="start" --id=2
```

## UI.Demo3
### UI.Demo3.react
### Code
- [project root](./demo3.react)
### Run
```shell
RUNNER_DOCKER_ARGS="--network=aeron -p 3000:3000" \
./do --component="ui" --command="start" --id=3.react
```
