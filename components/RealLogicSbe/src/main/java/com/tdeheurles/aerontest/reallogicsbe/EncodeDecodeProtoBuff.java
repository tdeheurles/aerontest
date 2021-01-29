package com.tdeheurles.aerontest.reallogicsbe;

import com.google.protobuf.InvalidProtocolBufferException;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class EncodeDecodeProtoBuff {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        final var start = Instant.now();
        final var demoClassBuilder = ProtoDemoClass.DemoClass.newBuilder();

        for (var i = 0; i < 10_000_000; i++) {
            // encode
            demoClassBuilder.clear();
            var builder = demoClassBuilder
                    .setSequence(123)
                    .setSampleEnum(ProtoDemoClass.SampleEnum.VALUE_1)
                    .setMessage("a message");
//            System.out.println("message: "+builder.toString());
            var serialized = builder.build().toByteArray();

            // decode
            var deserialized = ProtoDemoClass.DemoClass.parseFrom(serialized);
//            System.out.println("Sequence: "+deserialized.getSequence());
//            System.out.println("SampleEnum: "+deserialized.getSampleEnum());
//            System.out.println("Message: "+deserialized.getMessage());
        }

        final var finish = Instant.now();
        final long timeElapsed = Duration.between(start, finish).toMillis();

        System.out.println("time: "+timeElapsed);
    }
}
