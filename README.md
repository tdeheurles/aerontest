# AeronTest

These are my notes around the journey into Aeron.   

## Progressive Demos
Each `component` is organised in demo levels (demo0, demo1, etc). Then, the demos from each component are composed together to create other demos.
Intention is if you don't understand the code, you should be able to decrease the level of the demo to understand the `component` better and then go back to the higher level demo. 

### `UI` <--**WebSocket**--> `Babl` <--**Aeron**--> `ClusteredLogic`
  - [UI.Demo2](components/ui/README.md#demo2)
  - [Babl.Demo3](components/babl/README.md#demo3)
  - [Cluster.Demo2](components/ui/README.md#demo2)

## Elements
### Aeron
As defined on its github, Aeron is an efficient reliable UDP unicast, UDP multicast, and IPC message transport.
Reading the content on [the official github](https://github.com/real-logic/aeron) and [its wiki](https://github.com/real-logic/aeron/wiki) is certainly mandatory.
Another great source will be [the Aeron Cookbook](https://aeroncookbook.com/).
```markdown
# TODO:
- add the link to all aeron tutorials present somewhere on Aeron github
```

### AeronCluster
AeronCluster provides support for fault-tolerant services as replicated state machines based on the Raft consensus algorithm.

### Babl
Babl is a high-performance, scalable web-socket server designed for use in low-latency applications.
It's used here to connect the user (UI) with the server consistent logic (your logic running with the help of the AeronCluster).
Go to [its website](https://github.com/babl-ws/babl) and read the documentation.

### Agrona
Agrona provides a library of data structures and utility methods that are a common need when building high-performance applications in Java.
It's used in Aeron, Sbe, Babl.

### Protocol Buffer
Protocol buffers are Google's language-neutral, platform-neutral, extensible mechanism for serializing structured data.
Got to [its website](https://developers.google.com/protocol-buffers) and read the documentation.

### AtyosRunner
The AtyosRunner is here to glue the code all together. You can easily skip its usage if you don't want it.
