package p1;

import p1.Message;

public interface MessageProducer {
    int delay();

    int times();

    public int size();

    public Message nextMessage();

    default void info() {
        System.out.println("times=" + times() + ", delay=" + delay() + ", size=" + size() + "]");
    }
}
