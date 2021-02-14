package com.tdeheurles.aerontest.cluster;

import io.aeron.cluster.client.AeronCluster;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;

import static com.tdeheurles.aerontest.cluster.Configuration.INGRESS_ENDPOINTS;

public class Gateway {
    public void start(GatewayService gatewayService) {

        Configuration.AssertAndDumpClient();

        final String ingressEndpoints = System.getProperty(INGRESS_ENDPOINTS);

        try (
                var mediaDriver = MediaDriver.launchEmbedded(
                        new MediaDriver.Context()
                                .threadingMode(ThreadingMode.SHARED)
                                .dirDeleteOnStart(true)
                                .dirDeleteOnShutdown(true));

                var aeronCluster = AeronCluster.connect(
                        new AeronCluster.Context()
                                .egressListener(gatewayService)
                                .egressChannel("aeron:udp?endpoint=localhost:0")
                                .aeronDirectoryName(mediaDriver.aeronDirectoryName())
                                .ingressChannel("aeron:udp")
                                .ingressEndpoints(ingressEndpoints))) {

            gatewayService.onStart(aeronCluster);
        }
    }
}
