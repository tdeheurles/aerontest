package com.tdeheurles.aerontest.babl;

import com.aitusoftware.babl.user.ContentType;
import com.aitusoftware.babl.websocket.SendResult;
import com.aitusoftware.babl.websocket.Session;
import com.tdeheurles.aerontest.protobuf.Demo2Message;
import io.aeron.cluster.client.EgressListener;
import io.aeron.cluster.codecs.EventCode;
import io.aeron.logbuffer.Header;
import org.agrona.DirectBuffer;
import org.agrona.ExpandableDirectByteBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.Agent;

import java.util.Arrays;
import java.util.HashMap;

public class Demo2AdditionalWork implements Agent, EgressListener {
    private final HashMap<Long, Session> sessions;
    private final Demo2Message.Builder demo2MessageBuilder = Demo2Message.newBuilder();
    private final MutableDirectBuffer buffer = new ExpandableDirectByteBuffer(512);

    public Demo2AdditionalWork(HashMap<Long, Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public int doWork() {
        ConsoleLog.main_2("doWork");
        for (HashMap.Entry<Long, Session> entry : sessions.entrySet()) {
            System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());
            var session = entry.getValue();

            demo2MessageBuilder.clear();
            final var sendMessageString = "from Additional work";
            final var sendMessageOffset = 0;
            final var serialized = demo2MessageBuilder.setContent(sendMessageString).build().toByteArray();
            final var sendMessageLength = serialized.length;

            buffer.putBytes(sendMessageOffset, serialized);
            int sendResult;
            do {
                ConsoleLog.main_3("Sending: " + Arrays.toString(serialized));
                sendResult = session.send(ContentType.BINARY, buffer, sendMessageOffset, sendMessageLength);
            }
            while (sendResult != SendResult.OK);

            System.out.println("message sent with result " + sendResult);
            return sendResult;
        }

        return 0;
    }

    @Override
    public String roleName() {
        return "Demo1ApplicationLogic";
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
    }
}
