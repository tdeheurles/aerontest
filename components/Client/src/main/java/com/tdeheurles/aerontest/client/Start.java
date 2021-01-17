package com.tdeheurles.aerontest.client;

import io.aeron.Aeron;
import io.aeron.ConcurrentPublication;
import io.aeron.Publication;
import io.aeron.Subscription;
import org.agrona.concurrent.*;

import static java.lang.System.exit;

public class Start {

    private static final String CHANNEL = "aeron:ipc";
    private static final int STREAM_ID = 10;
    private static Aeron.Context aeronContext;
    private static Aeron aeron;
    private static Subscription subscription;
    private static ConcurrentPublication publication;

    public static void main(String[] args) {
        System.out.println("--- start with :"+String.join("", args)+" --- ");

        assertParameters(args);
        var instance = args[0];

        initialize(instance);
        startWorkers(instance);
    }

    private static void initialize(String instance) {
        var aeronDir = "/dev/shm/" + instance;
        System.out.println("aeronDir: " + aeronDir);

        aeronContext = new Aeron.Context().aeronDirectoryName(aeronDir);
        aeron = Aeron.connect(aeronContext);
        subscription = aeron.addSubscription(CHANNEL, STREAM_ID);
        publication = aeron.addPublication(CHANNEL, STREAM_ID);
    }

    private static void startWorkers(String instance) {
        final var sender = new Sender(publication);
        final var receiver = new Receiver(subscription);
        final var sleepStrategy = new SleepingMillisIdleStrategy(1000);
        final var errorHandler = new ErrorPrinter();

        final var agentRunnerSender = new AgentRunner(
                sleepStrategy, errorHandler, null, sender);
        final var agentRunnerReceiver = new AgentRunner(
                sleepStrategy, errorHandler, null, receiver);

        AgentRunner.startOnThread(agentRunnerSender);
        AgentRunner.startOnThread(agentRunnerReceiver);
    }

    private static void assertParameters(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide instance name as arg0");
            exit(1);
        }
    }
}
