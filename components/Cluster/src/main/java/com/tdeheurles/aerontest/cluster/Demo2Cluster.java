package com.tdeheurles.aerontest.cluster;

public class Demo2Cluster {

    public static void main(String[] args) {
        new Cluster().start(new Demo2ClusteredService());
    }
}
