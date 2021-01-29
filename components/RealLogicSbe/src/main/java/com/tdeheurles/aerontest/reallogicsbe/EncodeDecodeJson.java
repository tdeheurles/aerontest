package com.tdeheurles.aerontest.reallogicsbe;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

public class EncodeDecodeJson {
    public static void main(String[] args) {
        var start = Instant.now();

        var gson = new Gson();
        var demoClass = new DemoClass(123, SampleEnum.VALUE_1, "a message");

        for (var i = 0; i < 10_000_000; i++) {
            // encode
            var serialized = gson.toJson(demoClass);

            // decode
            var deserialized = gson.fromJson(serialized, DemoClass.class);

//            System.out.println("Size: "+serialized.length());
//            System.out.println("sequence: "+deserialized.sequence);
//            System.out.println("enumField: "+deserialized.enumField);
//            System.out.println("message: "+deserialized.message);
        }

        var finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        System.out.println("time: "+timeElapsed+" milliseconds");
    }
}
