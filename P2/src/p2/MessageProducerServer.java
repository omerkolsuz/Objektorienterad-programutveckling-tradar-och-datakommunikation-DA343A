package p2;

import p1.ArrayProducer;
import p1.MessageProducerInput;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageProducerServer {
    private Thread server;
    private MessageProducerInput producerInput;

    public MessageProducerServer(MessageProducerInput producerInput, int port) {
        this.producerInput = producerInput;
        server = new ServerManager(port);
    }

    public void startServer() {
        server.start();
    }

    public class ServerManager extends Thread {
        private int port;

        public ServerManager(int port) {
            this.port = port;
        }

        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("The Message Producer Server is active.");
                while (true) { // en while loop som gör att varje client får en socket
                    Socket socket = serverSocket.accept();
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ArrayProducer arrayProducer = (ArrayProducer) inputStream.readObject();
                    producerInput.addMessageProducer(arrayProducer);

                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
