package p2;


import p1.ArrayProducer;
import p1.Message;
import p1.MessageProducer;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageProducerClient {

    private String ip;
    private int port;

    public MessageProducerClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void send(MessageProducer messageProducer) {
        int times = messageProducer.times();
        int delay = messageProducer.delay();
        int size = messageProducer.size();

        Message[] messages = new Message[size];
        for (int i = 0; i < size; i++) {
            messages[i] = messageProducer.nextMessage();
        }

        ArrayProducer arrayProducer = new ArrayProducer(messages, times, delay);

        try {
            Socket socket = new Socket(ip, port);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(arrayProducer);
            outputStream.flush();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
