# UI - Demo

## UI.Demo0
- works with:
    - [Babl.Demo0](../babl/README.md#babldemo0)
- connect to server and send one simple message
- out are shown in the console
### Code
- [project root](./demo0)

## UI.Demo1
- works with:
  - [Babl.Demo0](../babl/README.md#babldemo0)
- changelog since [UI.Demo0](#uidemo0)
  - see https://golb.hplar.ch/2020/04/rxjs-websocket.html
  - use Angular
  - RxJsWebSocket
      - reconnection
### Code
- [project root](./demo1)

## UI.Demo2
- works with:
  - [messages.Demo3](../messages/README.md#messagesdemo2)
  - [Babl.Demo1](../babl/README.md#babldemo1) or [Babl.Demo2](../babl/README.md#babldemo2) or [Babl.Demo3](../babl/README.md#babldemo3)
- changelog since [UI.Demo1](#uidemo1)
  - RxJsWebSocket
      - reconnection
  - ProtocolBuffer
### Code
- [project root](./demo2)
### Run
```shell
RUNNER_DOCKER_ARGS="--network=aeron -p 4200:4200" \
./do --component="ui" --command="start" --id=2
```

## UI.Demo3
- works with:
  - [messages.Demo3](../messages/README.md#messagesdemo3)
  - [Babl.Demo4](../babl/README.md#babldemo4)
- changelog since [UI.Demo2](#uidemo2)
  - add [react Tic Tac Toe tutorial](https://reactjs.org/tutorial/tutorial.html) game logic
  - add code to handle messages from [messages.demo3](../messages/src/demo3.proto) 
### UI.Demo3.react
#### Code
- [project root](./demo3.angular)
#### Run
```shell
(
cd components/ui/demo3.angular 
npm install
)
```
```shell
RUNNER_DOCKER_ARGS="--network=aeron -p 4200:4200" \
./do --component="ui" --command="start" --id=3.angular
```
### UI.Demo3.react
`/!\ Issue with protocol buffer strict mode /!\`
#### Code
- [project root](./demo3.react)
#### Run
```shell
(
cd components/ui/demo3.react 
npm install
)
```
```shell
RUNNER_DOCKER_ARGS="--network=aeron -p 3000:3000" \
./do --component="ui" --command="start" --id=3.react
```
