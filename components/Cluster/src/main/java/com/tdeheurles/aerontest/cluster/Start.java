package com.tdeheurles.aerontest.cluster;

import com.tdeheurles.aerontest.utils.ConsoleColors;
import com.tdeheurles.aerontest.utils.ConsoleTheme;
import io.aeron.ChannelUriStringBuilder;
import io.aeron.CommonContext;
import io.aeron.archive.Archive;
import io.aeron.archive.ArchiveThreadingMode;
import io.aeron.archive.client.AeronArchive;
import io.aeron.cluster.ClusteredMediaDriver;
import io.aeron.cluster.ConsensusModule;
import io.aeron.cluster.service.ClusteredServiceContainer;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.MinMulticastFlowControlSupplier;
import io.aeron.driver.ThreadingMode;
import org.agrona.concurrent.NoOpLock;
import org.agrona.concurrent.ShutdownSignalBarrier;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public class Start {

    private static final String CLUSTER_NODE_ID = "cluster.node.id";
    private static final String ARCHIVE_CONTROL_REQUEST_PORT = "cluster.archive.control_request.port";
    private static final String CLIENT_FACING_PORT = "cluster.client_facing.port";
    private static final String MEMBER_FACING_PORT = "cluster.member_facing.port";
    private static final String LOG_PORT = "cluster.log.port";
    private static final String LOG_CONTROL_PORT = "cluster.log_control.port";
    private static final String TRANSFER_PORT = "cluster.transfer.port";
    private static final String MEMBERS_CONFIG = "cluster.members.config";
    private static final int TERM_LENGTH = 64 * 1024;

    public static void main(String[] args) {
        // PARAMETERS
        System.out.println(ConsoleTheme.MAIN0 + "=== AERON CLUSTER ===" + ConsoleColors.RESET);
        System.out.println(ConsoleTheme.MAIN1 + "Parameters:" + ConsoleColors.RESET);
        var properties = System.getProperties();
        Set.of(
                CLUSTER_NODE_ID,
                ARCHIVE_CONTROL_REQUEST_PORT,
                CLIENT_FACING_PORT,
                MEMBER_FACING_PORT,
                LOG_PORT,
                LOG_CONTROL_PORT,
                TRANSFER_PORT,
                MEMBERS_CONFIG
        ).forEach(elt -> {
            if (!properties.containsKey(elt)) {
                System.err.println(ConsoleTheme.ERROR0 + "Parameter " + elt + " is missing" + ConsoleColors.RESET);
                System.exit(1);
            }
            System.out.printf("  %-40s: %10s%n", elt, System.getProperty(elt));
        });
        System.out.println("");

        var nodeId = Integer.parseInt(System.getProperty(CLUSTER_NODE_ID));
        var archiveControlRequestPort = Integer.parseInt(System.getProperty(ARCHIVE_CONTROL_REQUEST_PORT));
        var clientFacingPort = Integer.parseInt(System.getProperty(CLIENT_FACING_PORT));
        var memberFacingPort = Integer.parseInt(System.getProperty(MEMBER_FACING_PORT));
        var logPort = Integer.parseInt(System.getProperty(LOG_PORT));
        var logControlPort = Integer.parseInt(System.getProperty(LOG_CONTROL_PORT));
        var transferPort = Integer.parseInt(System.getProperty(TRANSFER_PORT));
        var membersConfig = System.getProperty(MEMBERS_CONFIG);


        // CONFIGURE
        final var baseDir = new File(System.getProperty("user.dir"), "cluster_data/" + nodeId);
        System.out.println("baseDir: " + baseDir);
        final var aeronDirName = CommonContext.getAeronDirectoryName() + "-" + nodeId + "-driver";
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
                .endpoint("localhost:" + archiveControlRequestPort)
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
                        .controlEndpoint("localhost:" + logControlPort)
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
                        .archiveContext(aeronArchiveContext.clone());

        // ---- CLUSTERED SERVICE
        final var clusteredServiceContext =
                new ClusteredServiceContainer.Context()
                        .aeronDirectoryName(aeronDirName)
                        .archiveContext(aeronArchiveContext.clone())
                        .clusterDir(new File(baseDir, "service"))
                        .clusteredService(new DemoClusteredService())
                        .errorHandler(BasicErrorHandler.errorHandler("Clustered Service"));

        // RUN THE PROCESS
        try (
                var clusteredMediaDriver = ClusteredMediaDriver.launch(
                        mediaDriverContext, archiveContext, consensusModuleContext);
                var container = ClusteredServiceContainer.launch(
                        clusteredServiceContext);
        ) {
            shutdownBarrier.await();                                                                             // <3>
        }
    }
}
