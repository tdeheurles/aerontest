package com.tdeheurles.aerontest.reallogicsbe;

import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;

public class EncodeDecodeProtoBuff {
    public static void main(String[] args) {
        var start = Instant.now();

        for (var i = 0; i < 20_000_000; i++) {
            // encode

            // decode

        }

        var finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        System.out.println("time: "+timeElapsed);
    }
}
