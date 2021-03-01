package com.tdeheurles.aerontest.cluster;

import io.aeron.ExclusivePublication;
import io.aeron.Image;
import io.aeron.cluster.codecs.CloseReason;
import io.aeron.cluster.service.ClientSession;
import io.aeron.cluster.service.Cluster;
import io.aeron.cluster.service.ClusteredService;
import io.aeron.logbuffer.Header;
import org.agrona.DirectBuffer;

public class Demo1ClusteredService implements ClusteredService {
    public static final int ID_OFFSET = 0;

    @Override
    public void onStart(Cluster cluster, Image snapshotImage) {
        ConsoleLog.main_2("onStart");
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
        final long id = buffer.getLong(offset + ID_OFFSET);
        ConsoleLog.main_1("Received id: " + id);
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
