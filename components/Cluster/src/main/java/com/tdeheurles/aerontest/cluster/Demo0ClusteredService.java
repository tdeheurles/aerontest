package com.tdeheurles.aerontest.cluster;

import io.aeron.ExclusivePublication;
import io.aeron.Image;
import io.aeron.cluster.codecs.CloseReason;
import io.aeron.cluster.service.ClientSession;
import io.aeron.cluster.service.Cluster;
import io.aeron.cluster.service.ClusteredService;
import io.aeron.logbuffer.Header;
import org.agrona.DirectBuffer;

public class Demo0ClusteredService implements ClusteredService {

    @Override
    public void onStart(Cluster cluster, Image snapshotImage) {
        System.out.println("onStart");
    }

    @Override
    public void onSessionOpen(ClientSession session, long timestamp) {
        System.out.println("onSessionOpen("+session+")");
    }

    @Override
    public void onSessionClose(ClientSession session, long timestamp, CloseReason closeReason) {
        System.out.println("onSessionClose("+session+")");
    }

    @Override
    public void onSessionMessage(ClientSession session, long timestamp, DirectBuffer buffer, int offset, int length, Header header) {
        System.out.println("onSessionMessage(" + session + ") - timestamp(" + timestamp + ") - header(" + header + ")");
    }

    @Override
    public void onTimerEvent(long correlationId, long timestamp) {
        System.out.println("onTimerEvent");
    }

    @Override
    public void onTakeSnapshot(ExclusivePublication snapshotPublication) {
        System.out.println("onTakeSnapshot");
    }

    @Override
    public void onRoleChange(Cluster.Role newRole) {
        System.out.println("onRoleChange");
    }

    @Override
    public void onTerminate(Cluster cluster) {
        System.out.println("onTerminate");
    }
}
