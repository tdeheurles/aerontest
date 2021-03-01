package com.tdeheurles.aerontest.babl;

import com.aitusoftware.babl.config.BablConfig;
import com.aitusoftware.babl.config.PerformanceMode;
import com.aitusoftware.babl.user.Application;
import com.aitusoftware.babl.user.ContentType;
import com.aitusoftware.babl.websocket.*;
import com.tdeheurles.aerontest.protobuf.Demo2Message;
import org.agrona.DirectBuffer;
import org.agrona.ExpandableDirectByteBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.ShutdownSignalBarrier;

import java.util.Arrays;

public class Demo1Server implements Application {
    private final MutableDirectBuffer buffer = new ExpandableDirectByteBuffer(512);
    private final Demo2Message.Builder demo2MessageBuilder = Demo2Message.newBuilder();

    public static void main(String[] args) {

        var config = new BablConfig();
        config.performanceConfig().performanceMode(PerformanceMode.DEVELOPMENT);
        config.applicationConfig().application(new Demo1Server());
        config.sessionContainerConfig().listenPort(8081);
        config.sessionContainerConfig().serverDirectory("/babl/Server");

        try (SessionContainers sessionContainers = BablServer.launch(config)) {
            sessionContainers.start();
            ConsoleLog.main_0("Server ready");
            new ShutdownSignalBarrier().await();
        }
    }

    @Override
    public int onSessionConnected(Session session) {
        ConsoleLog.main_2("onSessionConnected");
        ConsoleLog.main_3(" - session.id(" + session.id() + ")");
        return SendResult.OK;
    }

    @Override
    public int onSessionDisconnected(Session session, DisconnectReason reason) {
        ConsoleLog.main_2("onSessionDisconnected");
        ConsoleLog.main_3(
                " - session.id(" + session.id() + ")\n" +
                " - DisconnectReason(" + reason.name() + ")");
        return 0;
    }

    @Override
    public int onSessionMessage(Session session, ContentType contentType, DirectBuffer msg, int offset, int length) {
        try {
            ConsoleLog.main_2("onSessionMessage:");
            ConsoleLog.main_3(
                    " - session.id(" + session.id() + ")\n" +
                            " - contentType(" + contentType + ")\n" +
                            " - directBuffer(" + msg + ")\n" +
                            " - offset(" + offset + ")\n" +
                            " - length(" + length + ")");

            // Receive
            final var messageBytes = msg.getStringWithoutLengthUtf8(offset, length).getBytes();
            final var demo2Message = Demo2Message.parseFrom(messageBytes);
            ConsoleLog.main_3("Message.content: " + demo2Message.getContent());

            // Send
            demo2MessageBuilder.clear();
            final var sendMessageString = "yehyehyeh";
            final var sendMessageOffset = 0;
            final var serialized = demo2MessageBuilder.setContent(sendMessageString).build().toByteArray();
            final var sendMessageLength = serialized.length;

            buffer.putBytes(sendMessageOffset, serialized);
            int sendResult;
            do {
                ConsoleLog.main_3("Sending: " + Arrays.toString(serialized));
                sendResult = session.send(contentType, buffer, sendMessageOffset, sendMessageLength);
            }
            while (sendResult != SendResult.OK);

            System.out.println("message sent with result " + sendResult);
            return sendResult;
        } catch (Exception e) {
            ConsoleLog.error_0("----------------------------------------------------------");
            e.printStackTrace();
            ConsoleLog.error_0("----------------------------------------------------------");
            return 1;
        }
    }
}
