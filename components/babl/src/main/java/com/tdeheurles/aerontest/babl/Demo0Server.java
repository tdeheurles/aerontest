package com.tdeheurles.aerontest.babl;

import com.aitusoftware.babl.config.BablConfig;
import com.aitusoftware.babl.config.PerformanceMode;
import com.aitusoftware.babl.user.Application;
import com.aitusoftware.babl.user.ContentType;
import com.aitusoftware.babl.websocket.BablServer;
import com.aitusoftware.babl.websocket.DisconnectReason;
import com.aitusoftware.babl.websocket.SendResult;
import com.aitusoftware.babl.websocket.Session;
import com.tdeheurles.aerontest.utils.ConsoleLog;
import org.agrona.DirectBuffer;
import org.agrona.ExpandableDirectByteBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.ShutdownSignalBarrier;

import java.util.Arrays;

public class Demo0Server implements Application {
    private final MutableDirectBuffer buffer = new ExpandableDirectByteBuffer(512);

    public static void main(String[] args) {

        var config = new BablConfig();
        config.performanceConfig().performanceMode(PerformanceMode.DEVELOPMENT);
        config.applicationConfig().application(new Demo0Server());
        config.sessionContainerConfig().listenPort(8081);
        config.sessionContainerConfig().serverDirectory("/babl/Server");

        try (var containers = BablServer.launch(config))
        {
            containers.start();
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
                " - DisconnectReason(" + reason.name() +")");
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
                            " - length(" + length + ")\n" +
                            " - msg -> " + msg.getStringWithoutLengthUtf8(offset, length));

            buffer.putBytes(0, msg, offset, length);
            int sendResult;
            do {
                System.out.println("sending buffer to session");
                sendResult = session.send(contentType, buffer, 0, length);
            }
            while (sendResult != SendResult.OK);

            System.out.println("message sent with result " + sendResult);
            return sendResult;
        } catch (Exception e) {
            ConsoleLog.error_0(Arrays.toString(e.getStackTrace()));
            return 1;
        }
    }
}
