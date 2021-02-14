package com.tdeheurles.aerontest.cluster;

public class Demo1Gateway {
    public static void main(String[] args) {
        new Gateway().start(new Demo1GatewayService());
    }
}
