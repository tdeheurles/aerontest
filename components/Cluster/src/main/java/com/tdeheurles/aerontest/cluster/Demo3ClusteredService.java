package com.tdeheurles.aerontest.cluster;

import com.tdeheurles.aerontest.protobuf.Demo3FullState;
import com.tdeheurles.aerontest.protobuf.Demo3Wrapper;
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

public class Demo3ClusteredService implements ClusteredService {
    private final Demo3Wrapper.Builder wrapperBuilder = Demo3Wrapper.newBuilder();
    private final Demo3FullState.Builder fullStateBuilder = Demo3FullState.newBuilder();
    private final MutableDirectBuffer buffer = new ExpandableDirectByteBuffer(512);
    private Cluster cluster;
    private Demo3GameLogic gameLogic;

    @Override
    public void onStart(Cluster cluster, Image snapshotImage) {
        ConsoleLog.main_2("onStart");
        this.cluster = cluster;
        this.gameLogic = new Demo3GameLogic();
    }

    @Override
    public void onSessionOpen(ClientSession session, long timestamp) {
        ConsoleLog.main_2("onSessionOpen");
        ConsoleLog.main_3("session(" + session + ") - timestamp(" + timestamp + ")");
    }

    @Override
    public void onSessionMessage(ClientSession session, long timestamp, DirectBuffer buffer, int offset, int length, Header header) {
        ConsoleLog.main_2("onSessionMessage");
        ConsoleLog.main_3("session(" + session + ") - timestamp(" + timestamp + ") - offset(" + offset + ") - length(" + length + ")");
        ConsoleLog.main_3("role: " + this.cluster.role().toString());

        try {
            final var messageBytes = buffer.getStringWithoutLengthUtf8(offset, length).getBytes();
            final var demo3Wrapper = Demo3Wrapper.parseFrom(messageBytes);
            switch (demo3Wrapper.getMessageCase()) {
                case FULLSTATEREQUEST -> {
                    ConsoleLog.main_3("fullStateRequest message received");
                    transmitFullStateOfTheGame(session);
                }
                case MOVE -> {
                    ConsoleLog.main_3("move message received");
                    final var move = demo3Wrapper.getMove();
                    gameLogic.calculateMove(move.getPosition());
                    transmitFullStateOfTheGame(session);
                }
                case RESET -> {
                    ConsoleLog.main_3("reset message received");
                    gameLogic.resetSquares();
                    transmitFullStateOfTheGame(session);
                }
            }
        }
        catch (Exception e) {
            ConsoleLog.error_0("----------------------------------------------------------");
            e.printStackTrace();
            ConsoleLog.error_0("----------------------------------------------------------");
        }
    }

    private void transmitFullStateOfTheGame(ClientSession session) {
        fullStateBuilder.clear();
        final var fullState = fullStateBuilder
                .addAllSquares(gameLogic.getFullState())
                .setXIsNext(gameLogic.getXIsNext())
                .setWinner(gameLogic.getWinner())
                .build();

        wrapperBuilder.clear();
        final var wrapper = wrapperBuilder.setFullState(fullState).build();

        final int sendOffset = 0;
        final int sendLength = wrapper.getSerializedSize();
        this.buffer.putBytes(sendOffset, wrapper.toByteArray());
        ConsoleLog.main_3("Sending.Message: " + wrapper.toString() + " of length(" + sendLength + ")");
        while (session.offer(this.buffer, sendOffset, sendLength) < 0) {
            ConsoleLog.main_3("Message not sent ...");
            this.cluster.idleStrategy().idle();
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
