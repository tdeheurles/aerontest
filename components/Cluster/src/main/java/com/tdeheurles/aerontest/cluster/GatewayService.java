package com.tdeheurles.aerontest.cluster;

import io.aeron.cluster.client.AeronCluster;
import io.aeron.cluster.client.EgressListener;

public interface GatewayService extends EgressListener {
    void onStart(AeronCluster aeronCluster);
}
