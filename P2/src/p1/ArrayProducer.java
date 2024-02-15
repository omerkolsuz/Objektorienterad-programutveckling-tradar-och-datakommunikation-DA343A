package p1;

import java.io.*;

public class ArrayProducer implements MessageProducer, Serializable {
    private Message[] messages;
    private int delay = 0;
    private int times = 0;
    private int aktuellIndex = -1;

    public ArrayProducer(Message[] messages, int times, int delay) {
        this.messages = messages;
        this.times = times;
        this.delay = delay;
    }

    @Override
    public int delay() {
        return delay;
    }

    @Override
    public int times() {
        return times;
    }

    @Override
    public int size() {
        return (messages == null) ? 0 : messages.length;
    }

    @Override
    public Message nextMessage() {
        if (size() == 0)
            return null;

        aktuellIndex = (aktuellIndex + 1) % messages.length;
        return messages[aktuellIndex];
    }
}
