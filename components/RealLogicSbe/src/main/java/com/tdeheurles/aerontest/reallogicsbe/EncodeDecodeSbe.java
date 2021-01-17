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

        for (var i = 0; i < 1_000_000; i++) {
            // encode
            encoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);
            encoder.sequence(123).enumField(SampleEnum.VALUE_1).message("a message");

            int encodedLength = MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();

            // decode
            int bufferOffset = 0;
            headerDecoder.wrap(directBuffer, bufferOffset);

            final var templateId = headerDecoder.templateId();
            if (templateId != SampleSimpleDecoder.TEMPLATE_ID) {
                throw new IllegalStateException("Template ids do not match");
            }

            final var actingBlockLength = headerDecoder.blockLength();
            final var actingVersion = headerDecoder.version();

            bufferOffset += headerDecoder.encodedLength();
            decoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);
        }

        var finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        System.out.println("time: "+timeElapsed);
    }
}
