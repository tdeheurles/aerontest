package com.tdeheurles.aerontest.babl;

import com.aitusoftware.babl.config.PropertiesLoader;
import com.aitusoftware.babl.websocket.BablServer;
import com.aitusoftware.babl.websocket.Session;
import io.aeron.cluster.client.AeronCluster;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;
import org.agrona.collections.Long2ObjectHashMap;
import org.agrona.concurrent.BackoffIdleStrategy;
import org.agrona.concurrent.ShutdownSignalBarrier;

import java.nio.file.Paths;

public class Demo4Server {
    public static void main(String[] args) {

        final var config = PropertiesLoader.configure(Paths.get(args[0]));
        final var ingressEndpoints = args[1];
        final var ip = args[2];
        final var aeronUdpEndpoint = "aeron:udp?endpoint=" + ip + ":0";

        System.out.println("ingressEndpoints: " + ingressEndpoints);
        System.out.println("ip:               " + ip);
        System.out.println("aeronUdpEndpoint: " + aeronUdpEndpoint);

        final var sessions = new Long2ObjectHashMap<Session>();
        final var additionalWorkClusterToGui = new Demo4AdditionalWorkerClusterToGui(sessions);

        try (
                var clusterMediaDriver = MediaDriver.launchEmbedded(
                        new MediaDriver.Context()
                                .threadingMode(ThreadingMode.SHARED)
                                .dirDeleteOnStart(true)
                                .aeronDirectoryName("babl_data/mediaDriver/cluster"));

                final var aeronCluster = AeronCluster.connect(
                        new AeronCluster.Context()
                                .egressListener(additionalWorkClusterToGui)
                                .egressChannel(aeronUdpEndpoint)
                                .aeronDirectoryName(clusterMediaDriver.aeronDirectoryName())
                                .ingressChannel("aeron:udp")
                                .ingressEndpoints(ingressEndpoints))
        ) {
            final var idleStrategy = new BackoffIdleStrategy();
            final var applicationGuiToCluster = new Demo4ApplicationGuiToCluster(sessions, aeronCluster, idleStrategy);
            additionalWorkClusterToGui.setAeronCluster(aeronCluster);

            config.applicationConfig().application(applicationGuiToCluster);
            config.applicationConfig().additionalWork(additionalWorkClusterToGui);

            try (var sessionContainers = BablServer.launch(config)) {
                sessionContainers.start();
                ConsoleLog.main_0("Server, ready to fire");
                new ShutdownSignalBarrier().await();
            }
        } catch (Exception e) {
            ConsoleLog.error_0("----------------------------------------------------------");
            e.printStackTrace();
            ConsoleLog.error_0("----------------------------------------------------------");
        }
    }
}
