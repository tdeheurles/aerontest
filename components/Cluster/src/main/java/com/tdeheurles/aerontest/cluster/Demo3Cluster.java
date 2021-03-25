package com.tdeheurles.aerontest.cluster;

public class Demo3Cluster {

    public static void main(String[] args) {
        new Cluster().start(new Demo3ClusteredService());
    }
}
