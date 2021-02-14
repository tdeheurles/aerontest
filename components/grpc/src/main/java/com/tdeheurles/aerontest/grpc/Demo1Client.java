package com.tdeheurles.aerontest.grpc;

import io.grpc.ManagedChannelBuilder;

public class Demo1Client {

    public static void main(String[] args) {
        System.out.println("Got a target in mind ?");
        var channel = ManagedChannelBuilder
                .forAddress("localhost", Configuration.PORT)
                .usePlaintext()
                .build();



        channel.shutdown();
    }
}
