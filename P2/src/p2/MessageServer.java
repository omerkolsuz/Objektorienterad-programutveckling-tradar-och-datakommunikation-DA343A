package p2;

import p1.Message;
import p1.MessageManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MessageServer implements PropertyChangeListener {

    private MessageManager messageManager;
    private int port;
    private ArrayList<ClientCommunication> clientCommunications;
    private Thread server;

    public MessageServer(MessageManager messageManager, int port) {
        this.messageManager = messageManager;
        this.port = port;
        clientCommunications = new ArrayList<>();
        messageManager.addMessageListener(this);
        server = new Connection(port);
        server.start();
    }

    public void sendMessage(Message message) {
        for (ClientCommunication clientCommunication : this.clientCommunications) {
            clientCommunication.put(message);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // if (evt.getPropertyName().equals("message")) { // felet som gjorde att bara 2 av 5 viewers funkade
        Message message = (Message) evt.getNewValue();
        sendMessage(message);

    }

    private class Connection extends Thread {
        private int port;

        public Connection(int port) {
            this.port = port;

        }

        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("The MessageServer is actively running and processing messages.");
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        ClientCommunication clientCommunication = new ClientCommunication(socket);
                        clientCommunication.start();
                        MessageServer.this.clientCommunications.add(clientCommunication);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class ClientCommunication extends Thread {
        private Socket socket;
        private ObjectOutputStream oos;

        public ClientCommunication(Socket socket) {
            this.socket = socket;

        }

        public void run() {

            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void put(Message message) {
            try {
                oos.writeObject(message);
                oos.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

