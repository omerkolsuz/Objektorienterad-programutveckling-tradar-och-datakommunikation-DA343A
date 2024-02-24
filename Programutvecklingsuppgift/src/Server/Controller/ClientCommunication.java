package Server.Controller;
/**
 * Class that handles the communication between one client and the server
 * @author Ömer - Oscar
 */

import GlobalModel.Message;
import GlobalModel.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientCommunication {
    private ObjectOutputStream outputStream;
    private Socket socket;
    private DataCommunication dataCommunication;
    private ServerController serverController;

    /**
     * Constructor that establishes the connection between the server and the Thread that listens to the client.
     * @param socket, the socket that connects to the client
     * @param serverCont, the server
     */
    public ClientCommunication(Socket socket, ServerController serverCont) {
        try {
            this.socket = socket;
            this.serverController = serverCont;
            dataCommunication = new DataCommunication(this);
            dataCommunication.start();
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Sends an object o to the client from the server
     * @param object
     */
    public void send(Object object) {
        try{
            outputStream.writeObject(object);
            outputStream.flush();
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Terminates the connection between the server and the client
     */
    public void closeClient() {
        if (!socket.isClosed() && socket.isConnected()){
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        dataCommunication.interrupt();
        dataCommunication = null;
        serverController.logOut(this);
    }
    /**
     * here handles the objects that are received from the client
     */
    public void handleInput(Object object) {
        if (object instanceof User){
            User user = (User) object;
            serverController.logIn(user, this);
        }
        else if (object instanceof Message){
            Message message = (Message) object;
            message.setTimeServer();
            if (!message.getLogout()){
                serverController.sendMessage(message);
            }
            else {
                closeClient();
            }
        }
    }

    /**
     * here vi get socket
     * @return
     */
    public Socket getSocket() {
        return socket;
    }


}