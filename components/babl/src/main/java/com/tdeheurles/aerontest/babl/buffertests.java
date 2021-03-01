package com.tdeheurles.aerontest.babl;

import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class buffertests {
    public static void main(String[] args) {
        final UnsafeBuffer buffer = new UnsafeBuffer(ByteBuffer.allocate(256));
        final UnsafeBuffer buffer2 = new UnsafeBuffer(ByteBuffer.allocate(256));
        buffer.putStringUtf8(0, "super");
        buffer2.putStringUtf8(0, buffer.getStringUtf8(0));
        ConsoleLog.main_3(buffer.getStringUtf8(0));
    }
}
