package com.tdeheurles.aerontest.babl;

import com.aitusoftware.babl.config.PropertiesLoader;
import com.aitusoftware.babl.websocket.*;
import io.aeron.cluster.client.AeronCluster;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;
import org.agrona.collections.Long2ObjectHashMap;
import org.agrona.concurrent.BackoffIdleStrategy;
import org.agrona.concurrent.ShutdownSignalBarrier;

import java.nio.file.Paths;

public class Demo3Server {
    public static void main(String[] args) {

        final var config = PropertiesLoader.configure(Paths.get(args[0]));
//        final var ingressEndpoints = args[1];

//        final var config = PropertiesLoader.configure(Paths.get(
//                "components/babl/src/main/resources/demo2.config.properties"));

//        var member_config = "0,";
//        member_config += "localhost:8001,";
//        member_config += "localhost:8002,";
//        member_config += "localhost:8003,";
//        member_config += "localhost:8004,";
//        member_config += "localhost:8000|";
//        member_config += "1,";
//        member_config += "localhost:8101,";
//        member_config += "localhost:8102,";
//        member_config += "localhost:8103,";
//        member_config += "localhost:8104,";
//        member_config += "localhost:8100|";
//        member_config += "2,";
//        member_config += "localhost:8201,";
//        member_config += "localhost:8202,";
//        member_config += "localhost:8203,";
//        member_config += "localhost:8204,";
//        member_config += "localhost:8200|";
        final var ingressEndpoints = "0=localhost:8001,1=localhost:8101,2=localhost:8201";
        final var sessions = new Long2ObjectHashMap<Session>();
        final var additionalWorkClusterToGui = new Demo3AdditionalWorkerClusterToGui(sessions);

        try (
                var clusterMediaDriver = MediaDriver.launchEmbedded(
                        new MediaDriver.Context()
                                .threadingMode(ThreadingMode.SHARED)
                                .dirDeleteOnStart(true)
                                .aeronDirectoryName("babl_data/mediaDriver/cluster"));

                final var aeronCluster = AeronCluster.connect(
                        new AeronCluster.Context()
                                .egressListener(additionalWorkClusterToGui)
                                .egressChannel("aeron:udp?endpoint=localhost:0")
                                .aeronDirectoryName(clusterMediaDriver.aeronDirectoryName())
                                .ingressChannel("aeron:udp")
                                .ingressEndpoints(ingressEndpoints))
        ) {
            final var idleStrategy = new BackoffIdleStrategy();
            final var applicationGuiToCluster = new Demo3ApplicationGuiToCluster(sessions, aeronCluster, idleStrategy);
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
