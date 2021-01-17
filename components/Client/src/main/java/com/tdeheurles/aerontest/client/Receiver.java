package com.tdeheurles.aerontest.client;

import io.aeron.Subscription;
import io.aeron.logbuffer.Header;
import org.agrona.DirectBuffer;
import org.agrona.concurrent.Agent;

public class Receiver implements Agent {
    private final Subscription subscription;

    public Receiver(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public int doWork() {
//        fragmentHandler = (buffer, offset, length, header) ->
//        {
//            final byte[] data = new byte[length];
//            buffer.getBytes(offset, data);
//
//
//        FragmentHandler fragmentAssembler = new FragmentAssembler(fragmentHandler);
//        final int fragmentsRead = subscription.poll(fragmentAssembler, 10);
        subscription.poll(this::handler, 1);
        System.out.println(this.roleName()+" end of doWork");
        return 0;
    }

    private void handler(DirectBuffer buffer, int offset, int length, Header header) {
        final int lastValue = buffer.getInt(offset);
        System.out.println("Hello from "+this.roleName()+" --> ("+lastValue+")");
    }

    @Override
    public String roleName() {
        return "receiver";
    }
}
