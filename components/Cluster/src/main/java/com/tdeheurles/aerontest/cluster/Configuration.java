package com.tdeheurles.aerontest.cluster;

import java.util.Set;

public class Configuration {
    public static final String CLUSTER_NODE_ID = "cluster.node.id";
    public static final String ARCHIVE_CONTROL_REQUEST_PORT = "cluster.archive.control_request.port";
    public static final String CLIENT_FACING_PORT = "cluster.client_facing.port";
    public static final String MEMBER_FACING_PORT = "cluster.member_facing.port";
    public static final String LOG_PORT = "cluster.log.port";
    public static final String LOG_CONTROL_PORT = "cluster.log_control.port";
    public static final String TRANSFER_PORT = "cluster.transfer.port";
    public static final String MEMBERS_CONFIG = "cluster.members.config";
    public static final String INGRESS_ENDPOINTS = "cluster.ingress.endpoints";
    public static final int TERM_LENGTH = 64 * 1024;

    public static void AssertAndDumpClient() {
        AssertAndDump(Set.of(
                INGRESS_ENDPOINTS
        ));
    }

    public static void AssertAndDumpCluster() {
        AssertAndDump(Set.of(
                CLUSTER_NODE_ID,
                ARCHIVE_CONTROL_REQUEST_PORT,
                CLIENT_FACING_PORT,
                MEMBER_FACING_PORT,
                LOG_PORT,
                LOG_CONTROL_PORT,
                TRANSFER_PORT,
                MEMBERS_CONFIG
        ));
    }

    private static void AssertAndDump(Set<String> configurations) {
        System.out.println("Configuration:");
        var properties = System.getProperties();
        configurations.forEach(elt -> {
            if (!properties.containsKey(elt)) {
                System.err.println("Parameter " + elt + " is missing");
                System.exit(1);
            }
            System.out.printf("  %-40s: %10s%n", elt, System.getProperty(elt));
        });
        System.out.println("");
    }
}
