package com.tdeheurles.aerontest.cluster;

public class Demo1Cluster {

    public static void main(String[] args) {
        new Cluster().start(new Demo1ClusteredService());
    }
}
