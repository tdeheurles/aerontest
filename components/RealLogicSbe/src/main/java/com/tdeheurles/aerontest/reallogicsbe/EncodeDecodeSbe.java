package com.tdeheurles.aerontest.reallogicsbe;

import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;

public class EncodeDecodeSbe {
    public static void main(String[] args) {
        var start = Instant.now();

        final var encoder = new SampleSimpleEncoder();
        final var messageHeaderEncoder = new MessageHeaderEncoder();

        final var byteBuffer = ByteBuffer.allocateDirect(128);
        final var directBuffer = new UnsafeBuffer(byteBuffer);

        final var decoder = new SampleSimpleDecoder();
        final var headerDecoder = new MessageHeaderDecoder();

        for (var i = 0; i < 10_000_000; i++) {
            // encode
            encoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);
            encoder.sequence(123).enumField(SampleEnum.VALUE_1).message("a message");

            // decode
            headerDecoder.wrap(directBuffer, 0);
            decoder.wrap(directBuffer, headerDecoder.encodedLength(), headerDecoder.blockLength(),
                    headerDecoder.version());
        }

        var finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        System.out.println("time: " + timeElapsed);
    }
}
