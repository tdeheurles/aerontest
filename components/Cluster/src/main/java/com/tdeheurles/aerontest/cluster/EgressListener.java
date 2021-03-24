package com.tdeheurles.aerontest.cluster;

import io.aeron.cluster.client.AeronCluster;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;

public class EgressListener {
    public void start(EgressListenerService egressListenerService) {

        final var address = "localhost";

        Configuration.AssertAndDumpClient();

        final String ingressEndpoints = System.getProperty(Configuration.INGRESS_ENDPOINTS);

        try (
                var mediaDriver = MediaDriver.launchEmbedded(
                        new MediaDriver.Context()
                                .threadingMode(ThreadingMode.SHARED)
                                .dirDeleteOnStart(true)
                                .dirDeleteOnShutdown(true));

                var aeronCluster = AeronCluster.connect(
                        new AeronCluster.Context()
                                .egressListener(egressListenerService)
                                .egressChannel("aeron:udp?endpoint=" + address + ":0")
                                .aeronDirectoryName(mediaDriver.aeronDirectoryName())
                                .ingressChannel("aeron:udp")
                                .ingressEndpoints(ingressEndpoints))) {
            egressListenerService.onStart(aeronCluster);
        }
    }
}
