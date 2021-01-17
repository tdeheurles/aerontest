package com.tdeheurles.aerontest.client;

import io.aeron.Publication;
import org.agrona.concurrent.Agent;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class Sender implements Agent {
    private final UnsafeBuffer unsafeBuffer;
    private int currentCountItem = 1;
    private final Publication publication;

    public Sender(final Publication publication) {
        this.publication = publication;
        unsafeBuffer = new UnsafeBuffer(ByteBuffer.allocate(64));
        unsafeBuffer.putInt(0, currentCountItem);
    }

    @Override
    public int doWork() {
        if (publication.isConnected()) {
            if (publication.offer(unsafeBuffer) > 0) {
                currentCountItem += 1;
                unsafeBuffer.putInt(0, currentCountItem);
            }
        }

        System.out.println(this.roleName()+" end of doWork");
        return 0;
    }

    @Override
    public String roleName() {
        return "sender";
    }
}
