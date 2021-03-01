package com.tdeheurles.aerontest.babl;

import com.aitusoftware.babl.user.Application;
import com.aitusoftware.babl.user.ContentType;
import com.aitusoftware.babl.websocket.DisconnectReason;
import com.aitusoftware.babl.websocket.SendResult;
import com.aitusoftware.babl.websocket.Session;
import com.tdeheurles.aerontest.protobuf.Demo2Message;
import io.aeron.cluster.client.AeronCluster;
import org.agrona.DirectBuffer;
import org.agrona.collections.Long2ObjectHashMap;
import org.agrona.concurrent.IdleStrategy;

public class Demo3ApplicationGuiToCluster implements Application {
    private final Long2ObjectHashMap<Session> sessions;
    private final AeronCluster aeronCluster;
    private final IdleStrategy idleStrategy;

    public Demo3ApplicationGuiToCluster(Long2ObjectHashMap<Session> sessions, AeronCluster aeronCluster, IdleStrategy idleStrategy) {
        this.sessions = sessions;
        this.aeronCluster = aeronCluster;
        this.idleStrategy = idleStrategy;
    }

    @Override
    public int onSessionConnected(Session session) {
        ConsoleLog.main_2("onSessionConnected");
        ConsoleLog.main_3(" - session.id(" + session.id() + ")");
        sessions.put(session.id(), session);
        ConsoleLog.main_3("session added");
        return SendResult.OK;
    }

    @Override
    public int onSessionDisconnected(Session session, DisconnectReason reason) {
        ConsoleLog.main_2("onSessionDisconnected");
        ConsoleLog.main_3(
            " - session.id(" + session.id() + ")\n" +
            " - DisconnectReason(" + reason.name() + ")");
        sessions.remove(session.id());
        ConsoleLog.main_3("session removed");
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

            // Print received message
            final var messageBytes = msg.getStringWithoutLengthUtf8(offset, length).getBytes();
            final var demo2Message = Demo2Message.parseFrom(messageBytes);
            ConsoleLog.main_3("Message.content: " + demo2Message.getContent());

            // Send message to cluster
            idleStrategy.reset();
            ConsoleLog.warning_0("Sending message ...");
            while (aeronCluster.offer(msg, offset, length) < 0) {
                ConsoleLog.warning_0("Message was not sent ...");
                idleStrategy.idle(aeronCluster.pollEgress());
            }

            return 0;
        } catch (Exception e) {
            ConsoleLog.error_0("----------------------------------------------------------");
            e.printStackTrace();
            ConsoleLog.error_0("----------------------------------------------------------");
            return 1;
        }
    }
}
