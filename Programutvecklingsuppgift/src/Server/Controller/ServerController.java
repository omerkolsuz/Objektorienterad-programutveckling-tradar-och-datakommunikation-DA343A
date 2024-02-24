package Server.Controller;
/**
 * The ServerController class represents the main controller of the chat server application.
 */

import GlobalModel.*;
import Server.Model.ClientConnection;
import Server.Model.UnsendMessages;
import Server.View.ServerView;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;


public class ServerController {
    private ClientConnection clientConnection = new ClientConnection();
    private UnsendMessages unsendMessages = new UnsendMessages();
    private FileWriter fileWriter;
    /**
     * Constructs a new ServerController object.
     * @param port The port number on which the server will listen for client connections.
     */
    public ServerController(int port) {
        new ServerConnection(port);
        try
        {
            new ServerView(InetAddress.getLocalHost(), port);
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        try{
            fileWriter = new FileWriter("files/ServerLog.txt", true);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        writeLog("ServerConnection är online nu!");
    }
    /**
     * Writes a log message to the server log file.
     * @param line The log message to be written.
     */
    private void writeLog(String line) {
        try{
            fileWriter.write("\n");
            fileWriter.write(LocalDateTime.now().toString());
            fileWriter.write("-------------------"+"\n");
            fileWriter.write(line);
            fileWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Logs in a user to the chat server.
     * @param user The user to be logged in.
     * @param cc The client communication object associated with the user.
     */
    public void logIn(User user, ClientCommunication cc){
        if (!clientConnection.hittaUser(user)){
            clientConnection.put(user,cc);
            sendOnlineList();
            sendUnsentMessages(user);
            writeLog(user.getUsername() + " har loggat in till servern!");
        }
        else {
            cc.closeClient();
            writeLog(user.getUsername()+" inloggning misslyckat :(");
        }
    }

    /**
     * Sends the current list of online users to all connected clients.
     */
    private void sendOnlineList() {
        for (User user : clientConnection.getOnlineListan()){
            clientConnection.getClientCommunication(user).send(clientConnection.getOnlineListan());
            writeLog("Online listan har skickats till: " + user.getUsername());
        }
    }
    /**
     * Logs out a user from the chat server.
     * @param cc The client communication object associated with the user to be logged out.
     */
    public void logOut(ClientCommunication cc){
        if (clientConnection.remove(cc)){
            sendOnlineList();
            writeLog("har loggat ut");
        }
        else{
            System.out.println("Kunde inte hitta client i Servern");
        }
    }
    /**
     * Sends a message to one or more recipients.
     * @param message The message to be sent.
     */

    public synchronized void sendMessage(Message message){
        for (User user : message.getReceivers()){
            if (clientConnection.hittaUser(user)){
                try{
                    clientConnection.getClientCommunication(user).send(message);
                    writeLog("Meddelandet skickades av client: " + message.getClient().getUsername() + " till: " + user.getUsername());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            else {
                unsendMessages.put(user,message);
                writeLog(user.getUsername() + "inte inloggad. Meddelandet sparas tills du loggar in igen.");
            }
        }
    }

    public synchronized void sendUnsentMessages(User user){
        if (unsendMessages.findUser(user)){
            for (Message m : unsendMessages.getMList(user)){
                try{
                    sendMessage(m);
                    writeLog("Meddelandet skickades från Servern");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * The Server class represents a thread that listens for client connections and creates a new ClientCommunication object for each one.
     */

    private class ServerConnection extends Thread{
        private ServerSocket serverSocket;

        public ServerConnection(int port){
            try{
                serverSocket = new ServerSocket(port);
                start();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        /**
         * The run method of the Server thread. Listens for client connections and creates a new ClientCommunication object for each one.
         */
        public void run(){
            while (!Thread.interrupted()){
                try {
                    new ClientCommunication(serverSocket.accept(), ServerController.this);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
