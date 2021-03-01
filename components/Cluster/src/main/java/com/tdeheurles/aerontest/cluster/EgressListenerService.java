package com.tdeheurles.aerontest.cluster;

import io.aeron.cluster.client.AeronCluster;
import io.aeron.cluster.client.EgressListener;

public interface EgressListenerService extends EgressListener {
    void onStart(AeronCluster aeronCluster);
}
