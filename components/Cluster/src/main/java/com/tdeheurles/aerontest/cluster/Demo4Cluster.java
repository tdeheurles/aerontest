package com.tdeheurles.aerontest.cluster;

public class Demo4Cluster {

    public static void main(String[] args) {
        new Cluster().start(new Demo4ClusteredService());
    }
}
