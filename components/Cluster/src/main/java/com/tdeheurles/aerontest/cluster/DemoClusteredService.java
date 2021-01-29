package com.tdeheurles.aerontest.cluster;

import com.tdeheurles.aerontest.utils.ConsoleColors;
import com.tdeheurles.aerontest.utils.ConsoleTheme;
import io.aeron.ExclusivePublication;
import io.aeron.Image;
import io.aeron.cluster.codecs.CloseReason;
import io.aeron.cluster.service.ClientSession;
import io.aeron.cluster.service.Cluster;
import io.aeron.cluster.service.ClusteredService;
import io.aeron.logbuffer.Header;
import io.aeron.samples.cluster.tutorial.BasicAuctionClusteredService;
import org.agrona.BitUtil;
import org.agrona.DirectBuffer;
import org.agrona.ExpandableDirectByteBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.collections.MutableBoolean;
import org.agrona.concurrent.IdleStrategy;

import java.util.Objects;

public class DemoClusteredService implements ClusteredService {
    public static final int ID_OFFSET = 0;
//    public static final int CORRELATION_ID_OFFSET = 0;
//    public static final int CUSTOMER_ID_OFFSET = CORRELATION_ID_OFFSET + BitUtil.SIZE_OF_LONG;
//    public static final int PRICE_OFFSET = CUSTOMER_ID_OFFSET + BitUtil.SIZE_OF_LONG;
//    public static final int BID_MESSAGE_LENGTH = PRICE_OFFSET + BitUtil.SIZE_OF_LONG;
//    public static final int BID_SUCCEEDED_OFFSET = BID_MESSAGE_LENGTH;
//    public static final int EGRESS_MESSAGE_LENGTH = BID_SUCCEEDED_OFFSET + BitUtil.SIZE_OF_BYTE;

//    public static final int SNAPSHOT_CUSTOMER_ID_OFFSET = 0;
//    public static final int SNAPSHOT_PRICE_OFFSET = SNAPSHOT_CUSTOMER_ID_OFFSET + BitUtil.SIZE_OF_LONG;
//    public static final int SNAPSHOT_MESSAGE_LENGTH = SNAPSHOT_PRICE_OFFSET + BitUtil.SIZE_OF_LONG;
//
    private final MutableDirectBuffer egressMessageBuffer = new ExpandableDirectByteBuffer(4);
//    private final MutableDirectBuffer snapshotBuffer = new ExpandableDirectByteBuffer(8);
//
//    // tag::state[]
//    private final Auction auction = new Auction();

    private IdleStrategy idleStrategy;
//    private Cluster cluster;

    @Override
    public void onStart(Cluster cluster, Image snapshotImage) {
//        this.cluster = cluster;
//        this.idleStrategy = cluster.idleStrategy();
//        if (null != snapshotImage)                   // <3>
//        {
//            loadSnapshot(cluster, snapshotImage);
//        }
        System.out.println("onStart");
    }

    @Override
    public void onSessionOpen(ClientSession session, long timestamp) {
        System.out.println("onSessionOpen("+session+")");
    }

    @Override
    public void onSessionClose(ClientSession session, long timestamp, CloseReason closeReason) {
        System.out.println("onSessionClose("+session+")");
    }

    @Override
    public void onSessionMessage(ClientSession session, long timestamp, DirectBuffer buffer, int offset, int length, Header header) {
        System.out.println("onSessionMessage(" + session + ") - timestamp(" + timestamp + ") - header(" + header + ")");
//        final long id = buffer.getLong(offset + ID_OFFSET);                   // <1>
//        System.out.println("Received id: " + id);
    }

    @Override
    public void onTimerEvent(long correlationId, long timestamp) {
        System.out.println("onTimerEvent");
    }

    @Override
    public void onTakeSnapshot(ExclusivePublication snapshotPublication) {
        System.out.println("onTakeSnapshot");
//        snapshotBuffer.putLong(CUSTOMER_ID_OFFSET, auction.getCurrentWinningCustomerId());    // <1>
//        snapshotBuffer.putLong(PRICE_OFFSET, auction.getBestPrice());
//
//        idleStrategy.reset();
//        while (snapshotPublication.offer(snapshotBuffer, 0, SNAPSHOT_MESSAGE_LENGTH) < 0)     // <2>
//        {
//            idleStrategy.idle();
//        }
    }

//    private void loadSnapshot(final Cluster cluster, final Image snapshotImage)
//    {
//        final MutableBoolean allDataLoaded = new MutableBoolean(false);
//
//        while (!snapshotImage.isEndOfStream())                                                       // <1>
//        {
//            final int fragmentsPolled = snapshotImage.poll(
//                    (buffer, offset, length, header) -> // <2>
//                    {
//                        assert length >= SNAPSHOT_MESSAGE_LENGTH;                                        // <3>
//
//                        final long customerId = buffer.getLong(offset + SNAPSHOT_CUSTOMER_ID_OFFSET);
//                        final long price = buffer.getLong(offset + SNAPSHOT_PRICE_OFFSET);
//
//                        auction.loadInitialState(price, customerId);                                     // <4>
//
//                        allDataLoaded.set(true);
//                    },
//                    1);
//
//            if (allDataLoaded.value)                                                                 // <5>
//            {
//                break;
//            }
//
//            idleStrategy.idle(fragmentsPolled);                                                      // <6>
//        }
//
//        assert snapshotImage.isEndOfStream();                                                        // <7>
//        assert allDataLoaded.value;
//    }

    @Override
    public void onRoleChange(Cluster.Role newRole) {
        System.out.println("onRoleChange");
    }

    @Override
    public void onTerminate(Cluster cluster) {
        System.out.println("onTerminate");
    }

//    private static class Auction
//    {
//        private long bestPrice = 0;
//        private long currentWinningCustomerId = -1;
//
//        public void loadInitialState(final long price, final long customerId)
//        {
//            bestPrice = price;
//            currentWinningCustomerId = customerId;
//        }
//
//        public boolean attemptBid(final long price, final long customerId)
//        {
//            System.out.println("attemptBid(this=" + this + ", price=" + price + ",customerId=" + customerId + ")");
//
//            if (price <= bestPrice)
//            {
//                return false;
//            }
//
//            bestPrice = price;
//            currentWinningCustomerId = customerId;
//
//            return true;
//        }
//
//        public long getBestPrice()
//        {
//            return bestPrice;
//        }
//
//        public long getCurrentWinningCustomerId()
//        {
//            return currentWinningCustomerId;
//        }
//
//        public boolean equals(final Object o)
//        {
//            if (this == o)
//            {
//                return true;
//            }
//
//            if (o == null || getClass() != o.getClass())
//            {
//                return false;
//            }
//
//            final Auction auction = (Auction)o;
//
//            return bestPrice == auction.bestPrice && currentWinningCustomerId == auction.currentWinningCustomerId;
//        }
//
//        public int hashCode()
//        {
//            return Objects.hash(bestPrice, currentWinningCustomerId);
//        }
//
//        public String toString()
//        {
//            return "Auction{" +
//                    "bestPrice=" + bestPrice +
//                    ", currentWinningCustomerId=" + currentWinningCustomerId +
//                    '}';
//        }
//    }
//
//    public boolean equals(final Object o)
//    {
//        if (this == o)
//        {
//            return true;
//        }
//
//        if (o == null || getClass() != o.getClass())
//        {
//            return false;
//        }
//
//        final DemoClusteredService that = (DemoClusteredService)o;
//
//        return auction.equals(that.auction);
//    }
//
//    public int hashCode()
//    {
//        return Objects.hash(auction);
//    }
//
//    public String toString()
//    {
//        return "BasicAuctionClusteredService{" +
//                "auction=" + auction +
//                '}';
//    }
}
