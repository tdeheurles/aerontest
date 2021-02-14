package com.tdeheurles.aerontest.grpc;

import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class Demo1Server extends Demo1ServiceGrpc.Demo1ServiceImplBase {

    @Override
    public void Demo1(Demo1Request request, StreamObserver<Demo1Response> observer) {
        var name = request.getFirstName() + " " + request.getLastName();
        System.out.println(name + " is firing at us");
        var greeting = "go fuck " + name;
        var response = Demo1Response.newBuilder()
                .setGreeting(greeting)
                .build();

        observer.onNext(response);
        observer.onCompleted();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Server().Start(new Demo1Server());
    }
}
