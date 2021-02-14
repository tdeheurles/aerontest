package com.tdeheurles.aerontest.grpc;

import io.grpc.BindableService;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Server {
    public void Start(BindableService bindableService) throws IOException, InterruptedException {
        var server = ServerBuilder
                .forPort(Configuration.PORT)
                .addService(bindableService)
                .build();

        server.start();
        System.out.println("Channel's clear, boss !");
        server.awaitTermination();
    }
}
