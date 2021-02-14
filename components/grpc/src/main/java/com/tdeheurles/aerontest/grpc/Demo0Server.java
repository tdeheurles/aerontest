package com.tdeheurles.aerontest.grpc;

import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class Demo0Server extends Demo0ServiceGrpc.Demo0ServiceImplBase {

    @Override
    public void hello(Demo0Request request, StreamObserver<Demo0Response> observer) {
        var name = request.getFirstName() + " " + request.getLastName();
        System.out.println(name + " is firing at us");
        var greeting = "don't waste my time " + name;
        var response = Demo0Response.newBuilder()
                .setGreeting(greeting)
                .build();

        observer.onNext(response);
        observer.onCompleted();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Server().Start(new Demo0Server());
    }
}
