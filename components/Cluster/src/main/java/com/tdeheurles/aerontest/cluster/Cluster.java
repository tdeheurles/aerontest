package com.tdeheurles.aerontest.cluster;

import io.aeron.ChannelUriStringBuilder;
import io.aeron.CommonContext;
import io.aeron.archive.Archive;
import io.aeron.archive.ArchiveThreadingMode;
import io.aeron.archive.client.AeronArchive;
import io.aeron.cluster.ClusteredMediaDriver;
import io.aeron.cluster.ConsensusModule;
import io.aeron.cluster.service.ClusteredService;
import io.aeron.cluster.service.ClusteredServiceContainer;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.MinMulticastFlowControlSupplier;
import io.aeron.driver.ThreadingMode;
import org.agrona.concurrent.NoOpLock;
import org.agrona.concurrent.ShutdownSignalBarrier;

import java.io.File;

import static com.tdeheurles.aerontest.cluster.Configuration.*;

public class Cluster {

    public void start(ClusteredService clusteredService) {

        AssertAndDumpCluster();

        var archiveControlRequestPort = Integer.parseInt(System.getProperty(ARCHIVE_CONTROL_REQUEST_PORT));
        var logControlPort = Integer.parseInt(System.getProperty(LOG_CONTROL_PORT));
        var membersConfig = System.getProperty(MEMBERS_CONFIG);
        final var ip = System.getProperty(IP);

        // -- CONFIGURE
        var nodeId = Integer.parseInt(System.getProperty(CLUSTER_NODE_ID));
        final var baseDir = new File(System.getProperty("user.dir"), "cluster_data/" + nodeId);
        final var aeronDirName = CommonContext.getAeronDirectoryName() + "-" + nodeId + "-driver";
        System.out.println("baseDir: " + baseDir);
        final var shutdownBarrier = new ShutdownSignalBarrier();

        // ---- MEDIA DRIVER
        final var mediaDriverContext = new MediaDriver.Context()
                .aeronDirectoryName(aeronDirName)
                .threadingMode(ThreadingMode.SHARED)
                .termBufferSparseFile(true)
                .multicastFlowControlSupplier(new MinMulticastFlowControlSupplier())
                .terminationHook(shutdownBarrier::signal)
                .errorHandler(BasicErrorHandler.errorHandler("Media Driver"));

        // ---- ARCHIVE
        var archiveContextUdpChannel = new ChannelUriStringBuilder()
                .media("udp")
                .termLength(TERM_LENGTH)
                .endpoint(ip + ":" + archiveControlRequestPort)
                .build();
        System.out.println("archiveContextUdpChannel: " + archiveContextUdpChannel);
        final var archiveContext = new Archive.Context()
                .aeronDirectoryName(aeronDirName)
                .archiveDir(new File(baseDir, "archive"))
                .controlChannel(archiveContextUdpChannel)
                .localControlChannel("aeron:ipc?term-length=64k")
                .recordingEventsEnabled(false)
                .threadingMode(ArchiveThreadingMode.SHARED);

        // ---- AERON ARCHIVE
        final var aeronArchiveContext = new AeronArchive.Context()
                .lock(NoOpLock.INSTANCE)
                .controlRequestChannel(archiveContext.localControlChannel())
                .controlRequestStreamId(archiveContext.localControlStreamId())
                .controlResponseChannel(archiveContext.localControlChannel())
                .aeronDirectoryName(aeronDirName);

        // ---- CONSENSUS MODULE
        System.out.println("membersConfig: " + membersConfig);
        var logControlChannel =
                new ChannelUriStringBuilder()
                        .media("udp")
                        .termLength(TERM_LENGTH)
                        .controlMode(CommonContext.MDC_CONTROL_MODE_MANUAL)
                        .controlEndpoint(ip + ":" + logControlPort)
                        .build();
        System.out.println("logControlChannel: " + logControlChannel);
        final var consensusModuleContext =
                new ConsensusModule.Context()
                        .errorHandler(BasicErrorHandler.errorHandler("Consensus Module"))
                        .clusterMemberId(nodeId)
                        .clusterMembers(membersConfig)
                        .clusterDir(new File(baseDir, "consensus-module"))
                        .ingressChannel("aeron:udp?term-length=64k")
                        .logChannel(logControlChannel)
                        .archiveContext(aeronArchiveContext.clone())
                        .leaderHeartbeatTimeoutNs(1_000_000_000);

        var clusteredServiceContainerContext = new ClusteredServiceContainer.Context()
                .aeronDirectoryName(aeronDirName)
                .archiveContext(aeronArchiveContext.clone())
                .clusterDir(new File(baseDir, "service"))
                .clusteredService(clusteredService)
                .errorHandler(BasicErrorHandler.errorHandler("Clustered Service"));

        // RUN THE PROCESS
        try (
                var ignored = ClusteredMediaDriver.launch(
                        mediaDriverContext, archiveContext, consensusModuleContext);
                var ignored1 = ClusteredServiceContainer.launch(
                        clusteredServiceContainerContext);
        ) {
            shutdownBarrier.await();                                                                             // <3>
        }
    }
}
