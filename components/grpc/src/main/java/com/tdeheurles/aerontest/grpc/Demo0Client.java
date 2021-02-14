package com.tdeheurles.aerontest.grpc;

import io.grpc.ManagedChannelBuilder;

public class Demo0Client {

    public static void main(String[] args) {
        System.out.println("Got a target in mind ?");
        var channel = ManagedChannelBuilder
                .forAddress("localhost", Configuration.PORT)
                .usePlaintext()
                .build();

        Demo0ServiceGrpc.Demo0ServiceBlockingStub blockingStub
                = Demo0ServiceGrpc.newBlockingStub(channel);

        var helloResponse = blockingStub.hello(Demo0Request.newBuilder()
                .setFirstName("Thibault")
                .setLastName("Deheurles")
                .build());

        System.out.println("Server have answered with " + helloResponse.getGreeting());

        channel.shutdown();
    }
}
