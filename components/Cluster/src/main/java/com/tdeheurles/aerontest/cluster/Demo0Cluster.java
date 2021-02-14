package com.tdeheurles.aerontest.cluster;

public class Demo0Cluster {
    public static void main(String[] args) {
        new Cluster().start(new Demo0ClusteredService());
    }
}
