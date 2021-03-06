package io.jaq.spsc;

import io.jaq.mpmc.MpmcConcurrentQueue;
import io.jaq.mpmc.MpmcConcurrentQueueStateMarkers;
import io.jaq.mpsc.MpscCompoundQueue;
import io.jaq.mpsc.MpscConcurrentQueue;
import io.jaq.mpsc.MpscOnSpscQueue;
import io.jaq.spmc.SpmcConcurrentQueue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedTransferQueue;

public class SPSCQueueFactory {
    public static final int CAPACITY = 1 << 14;
    public static final int QUEUE_CAPACITY = 1 << Integer.getInteger("pow2.capacity", 15);
    public static final int QUEUE_TYPE = Integer.getInteger("q.type", 0);
    public static Queue<Integer> createQueue() {
        switch (QUEUE_TYPE) {
        case -99:
            return new ArrayDeque<Integer>(QUEUE_CAPACITY);
        case -3:
            return new ArrayBlockingQueue<Integer>(QUEUE_CAPACITY);
        case -2:
            return new LinkedTransferQueue<Integer>();
        case -1:
            return new ConcurrentLinkedQueue<Integer>();
        case 0:
            return new InlinedCountersSpscConcurrentArrayQueue<Integer>(QUEUE_CAPACITY);
        case 10:
            return new BQueue<Integer>(QUEUE_CAPACITY);
        case 20:
            return new FFBuffer<Integer>(QUEUE_CAPACITY);
        case 3:
            return new FFBufferWithOfferBatch<Integer>(QUEUE_CAPACITY);
        case 31:
            return new SpscLinkedQueue<Integer>();
        case 40:
            return new FloatingCountersSpscConcurrentArrayQueue<Integer>(QUEUE_CAPACITY);
        case 5:
            return new SpmcConcurrentQueue<Integer>(QUEUE_CAPACITY);
        case 6:
            return new MpscConcurrentQueue<Integer>(QUEUE_CAPACITY);
        case 61:
            return new MpscCompoundQueue<Integer>(QUEUE_CAPACITY);
        case 62:
            return new MpscOnSpscQueue<Integer>(QUEUE_CAPACITY);
        case 7:
            return new MpmcConcurrentQueue<Integer>(QUEUE_CAPACITY);
        case 71:
            return new MpmcConcurrentQueueStateMarkers<Integer>(QUEUE_CAPACITY);
        }
        throw new IllegalArgumentException("Type: " + QUEUE_TYPE);
    }

}
