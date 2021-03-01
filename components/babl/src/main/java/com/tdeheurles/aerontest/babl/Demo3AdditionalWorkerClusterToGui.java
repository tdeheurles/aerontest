package com.tdeheurles.aerontest.babl;

import com.aitusoftware.babl.user.ContentType;
import com.aitusoftware.babl.websocket.SendResult;
import com.aitusoftware.babl.websocket.Session;
import com.tdeheurles.aerontest.protobuf.Demo2Message;
import io.aeron.cluster.client.AeronCluster;
import io.aeron.cluster.client.EgressListener;
import io.aeron.cluster.codecs.EventCode;
import io.aeron.logbuffer.Header;
import org.agrona.DirectBuffer;
import org.agrona.ExpandableDirectByteBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.collections.Long2ObjectHashMap;
import org.agrona.concurrent.Agent;

import java.util.HashMap;

public class Demo3AdditionalWorkerClusterToGui implements Agent, EgressListener {
    private final Long2ObjectHashMap<Session> sessions;
    private AeronCluster aeronCluster;
    private final Demo2Message.Builder demo2MessageBuilder = Demo2Message.newBuilder();
    private final MutableDirectBuffer buffer = new ExpandableDirectByteBuffer(512);

    public Demo3AdditionalWorkerClusterToGui(Long2ObjectHashMap<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public int doWork() {
        if (aeronCluster != null) {
            aeronCluster.sendKeepAlive();
            aeronCluster.pollEgress();
        }
        return 0;
    }

    @Override
    public String roleName() {
        return "Demo3ApplicationLogic";
    }

    @Override
    public void onSessionEvent(long correlationId, long clusterSessionId, long leadershipTermId, int leaderMemberId, EventCode code, String detail) {
        ConsoleLog.main_2("onSessionEvent");
        ConsoleLog.main_3(
                "correlationId(" + correlationId + ") - " +
                "clusterSessionId(" + clusterSessionId + ") - " +
                "leadershipTermId(" + leadershipTermId + ") - " +
                "leaderMemberId(" + leaderMemberId + ") - " +
                "code(" + code + ") - " +
                "detail(" + detail + ")");
    }

    @Override
    public void onNewLeader(long clusterSessionId, long leadershipTermId, int leaderMemberId, String ingressEndpoints) {
        ConsoleLog.main_2("onNewLeader");
        ConsoleLog.main_3(
                "clusterSessionId(" + clusterSessionId + ") - " +
                "leadershipTermId(" + leadershipTermId + ") - " +
                "leaderMemberId(" + leaderMemberId + ") - " +
                "ingressEndpoints(" + ingressEndpoints + ")");
    }

    @Override
    public void onMessage(long clusterSessionId, long timestamp, DirectBuffer buffer, int offset, int length, Header header) {
        ConsoleLog.main_2("onMessage");
        ConsoleLog.main_3(
                "clusterSessionId(" + clusterSessionId + ") - " +
                "timestamp(" + timestamp + ")");

        try {
            // Print message received from cluster
//            final var messageBytes = buffer.getStringWithoutLengthUtf8(offset, length).getBytes();
//            final var demo2Message = Demo2Message.parseFrom(messageBytes);

            // Send message to UI
            for (HashMap.Entry<Long, Session> entry : sessions.entrySet()) {
                var session = entry.getValue();

                int sendResult;
                do {
//                    ConsoleLog.main_3("Transferring message from cluster to ui: " + demo2Message.getContent());
                    sendResult = session.send(ContentType.BINARY, buffer, offset, length);
                }
                while (sendResult != SendResult.OK);

                System.out.println("message sent with result " + sendResult);
            }
        }
        catch (Exception e) {
            ConsoleLog.error_0("----------------------------------------------------------");
            e.printStackTrace();
            ConsoleLog.error_0("----------------------------------------------------------");
        }
    }

    public void setAeronCluster(AeronCluster aeronCluster) {
        this.aeronCluster = aeronCluster;
    }
}
