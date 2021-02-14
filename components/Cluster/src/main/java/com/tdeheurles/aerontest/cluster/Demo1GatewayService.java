package com.tdeheurles.aerontest.cluster;

import com.tdeheurles.aerontest.utils.ConsoleLog;
import io.aeron.cluster.client.AeronCluster;
import io.aeron.cluster.codecs.EventCode;
import io.aeron.logbuffer.Header;
import org.agrona.BitUtil;
import org.agrona.DirectBuffer;
import org.agrona.ExpandableDirectByteBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.BackoffIdleStrategy;
import org.agrona.concurrent.IdleStrategy;

public class Demo1GatewayService implements GatewayService {

    private final IdleStrategy idleStrategy = new BackoffIdleStrategy();
    private final MutableDirectBuffer buffer = new ExpandableDirectByteBuffer();

    public static final String ID = "client.id";

    public static final int ID_OFFSET = 0;
    public static final int MESSAGE_LENGTH = BitUtil.SIZE_OF_INT;

    @Override
    public void onStart(AeronCluster aeronCluster) {

        final int id = Integer.parseInt(System.getProperty(ID));

        System.out.println("Sending id(" + id + ")");
        buffer.putInt(ID_OFFSET, id);

        idleStrategy.reset();
        while (aeronCluster.offer(buffer, 0, MESSAGE_LENGTH) < 0) {
            idleStrategy.idle(aeronCluster.pollEgress());
        }
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
