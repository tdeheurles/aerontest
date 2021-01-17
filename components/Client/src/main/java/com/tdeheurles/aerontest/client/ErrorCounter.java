package com.tdeheurles.aerontest.client;

import org.agrona.concurrent.AtomicBuffer;
import org.agrona.concurrent.status.AtomicCounter;

public class ErrorCounter extends AtomicCounter {
    public ErrorCounter(AtomicBuffer buffer, int counterId) {
        super(buffer, counterId);
    }
}
