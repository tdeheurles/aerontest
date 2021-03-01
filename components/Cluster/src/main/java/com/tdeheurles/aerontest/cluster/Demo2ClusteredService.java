package com.tdeheurles.aerontest.cluster;

import com.tdeheurles.aerontest.protobuf.Demo2Message;
import io.aeron.ExclusivePublication;
import io.aeron.Image;
import io.aeron.cluster.codecs.CloseReason;
import io.aeron.cluster.service.ClientSession;
import io.aeron.cluster.service.Cluster;
import io.aeron.cluster.service.ClusteredService;
import io.aeron.logbuffer.Header;
import org.agrona.DirectBuffer;
import org.agrona.ExpandableDirectByteBuffer;
import org.agrona.MutableDirectBuffer;

public class Demo2ClusteredService implements ClusteredService {
    private final Demo2Message.Builder demoClassBuilder = Demo2Message.newBuilder();
    private final MutableDirectBuffer buffer = new ExpandableDirectByteBuffer(512);
    private Cluster cluster;

    @Override
    public void onStart(Cluster cluster, Image snapshotImage) {
        ConsoleLog.main_2("onStart");
        this.cluster = cluster;
    }

    @Override
    public void onSessionOpen(ClientSession session, long timestamp) {
        ConsoleLog.main_2("onSessionOpen");
        ConsoleLog.main_3("session(" + session + ") - timestamp(" + timestamp + ")");
    }

    @Override
    public void onSessionMessage(ClientSession session, long timestamp, DirectBuffer buffer, int offset, int length, Header header) {
        ConsoleLog.main_2("onSessionMessage");
        ConsoleLog.main_3("session(" + session + ") - timestamp(" + timestamp + ") - offset(" + offset + ")");

        try {
            // Print message received from cluster
            final var messageBytes = buffer.getStringWithoutLengthUtf8(offset, length).getBytes();
            final var demo2Message = Demo2Message.parseFrom(messageBytes);
            ConsoleLog.main_3("Received.Message.Content: " + demo2Message.getContent());

            // Send an answer to EgressListener
            demoClassBuilder.clear();
            final var builder = demoClassBuilder.setContent("answer built in the cluster").build();
            final int sendOffset = 0;
            final int sendLength = builder.getSerializedSize();
            this.buffer.putBytes(sendOffset, builder.toByteArray());
            ConsoleLog.main_3("Sending.Message: " + builder.toString());
            while (session.offer(this.buffer, sendOffset, sendLength) < 0) {
                ConsoleLog.main_3("Message not sent ...");
                this.cluster.idleStrategy().idle();
            }
        }
        catch (Exception e) {
            ConsoleLog.error_0("----------------------------------------------------------");
            e.printStackTrace();
            ConsoleLog.error_0("----------------------------------------------------------");
        }
    }

    @Override
    public void onSessionClose(ClientSession session, long timestamp, CloseReason closeReason) {
        ConsoleLog.main_2("onSessionClose");
        ConsoleLog.main_3("session(" + session + ") - timestamp(" + timestamp + ") - closeReason(" + closeReason + ")\n");
    }

    @Override
    public void onTimerEvent(long correlationId, long timestamp) {
        ConsoleLog.main_2("onTimerEvent");
        ConsoleLog.main_3("correlationId(" + correlationId + ") - timestamp(" + timestamp + ")");
    }

    @Override
    public void onTakeSnapshot(ExclusivePublication snapshotPublication) {
        ConsoleLog.main_2("onTakeSnapshot");
        ConsoleLog.main_3("snapshotPublication(" + snapshotPublication + ")");
    }

    @Override
    public void onRoleChange(Cluster.Role newRole) {
        ConsoleLog.main_2("onRoleChange");
        ConsoleLog.main_3("newRole(" + newRole + ")");
    }

    @Override
    public void onTerminate(Cluster cluster) {
        ConsoleLog.main_2("onTerminate");
        ConsoleLog.main_3("cluster(" + cluster + ")");
    }
}
