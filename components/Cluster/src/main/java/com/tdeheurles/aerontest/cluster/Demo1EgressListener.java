package com.tdeheurles.aerontest.cluster;

public class Demo1EgressListener {
    public static void main(String[] args) {
        new EgressListener().start(new Demo1EgressListenerService());
    }
}
