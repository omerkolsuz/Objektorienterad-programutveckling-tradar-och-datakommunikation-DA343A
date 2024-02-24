package Client.Controller;

/**
 * Klassen ClientController ansvarar för att hantera klientdelen av chattapplikationen.
 * Den hanterar användarautentisering, sändning och mottagning av meddelanden och upprätthåller
 * listan över online- och sparade kontakter.
 * @author Ömer Kolsuz - Oscar Svantesson
 */

import Client.View.MainView.MainFrame;
import GlobalModel.*;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientController {
    private MainFrame view;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private String ip;
    private int port;
    private User user;
    private Thread input;
    private PropertyChangeSupport changeSupport;
    private ArrayList<User> onlineUsers = new ArrayList<>();
    private ArrayList<User> savedUsers = new ArrayList<>();
    private Socket socket;
    private Message message;
    private HashMap<User, ArrayList<User>> contacsBin;
    private String file;
    private ImageIcon image;
    private String userName;

    /**
     * Skapar en ny instans av klassen ClientController.
     *
     * @param ip IP-adressen för servern att ansluta till
     * @param port portnumret för servern att ansluta till
     * @param name användarens namn
     */

    public ClientController(String ip, int port, String name) {
        changeSupport = new PropertyChangeSupport(this);
        this.ip = ip;
        this.port = port;
        this.view = new MainFrame(this, name);
        this.userName = name; // new field
    }

    /**
     * Ansluter klienten till servern med det angivna namnet och bildsökvägen.
     *
     * @param name användarens namn
     * @param path sökvägen till användarens bildfil
     * @return true om anslutningen lyckas, false annars
     *
     */
    public boolean connect(String name, String path){
        try{
            socket = new Socket(ip,port);
            user=null;
            user = new User(name,new ImageIcon(path));
            try{
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputStream.writeObject(user);
                outputStream.flush();
                outputStream.writeObject(user);
                outputStream.flush();
                if (input == null){
                    input = new Receiver();
                    input.start();
                }
            }catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Kopplar från klienten från servern.
     */

    public void disconnect(){
        try{
            outputStream.writeObject(new Message(true,this.user));
            if (inputStream != null){
                inputStream.close();
            }
            if (outputStream != null){
                outputStream.close();
            }
            if (socket != null){
                socket.close();
            }
            user = null;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Skickar ett meddelande till de valda mottagarna.
     *
     * @param text meddelandetexten
     * @param receiversUserNames användarnamnen för mottagarna
     * @param icon bilden som ska skickas med meddelandet (valfri)

     */
    public synchronized void sendMessage(String text, ArrayList<String> receiversUserNames, ImageIcon icon){
        try{
            ArrayList<User> receivers = new ArrayList<>();
            for (String UserNames : receiversUserNames){
                for (User onlineUser : onlineUsers){
                    if (UserNames.equals(onlineUser.getUsername())){
                        receivers.add(onlineUser);
                    }
                }
                for (User savedUser : savedUsers){
                    if (UserNames.equals(savedUser.getUsername()) && !receivers.contains(savedUser)){
                        receivers.add(savedUser);
                    }
                }
            }
            Message m = new Message(text, this.user, receivers, icon);
            outputStream.writeObject(m);
            outputStream.flush();
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Saves the selected user to the list of saved contacts.
     *
     * @param userName the username of the user to be saved
     */

    public void saveContact(String userName) {
        try {
            if (userName.equals(user.getUsername())) {

                return;
            }

            boolean contactSaved = false;
            ArrayList<User> contacts = new ArrayList<>();
            String filename = "users/" + this.userName + ".bin";
            File file = new File(filename);
            if (!file.exists()) {
                ObjectOutputStream newOutput = new ObjectOutputStream(
                        new FileOutputStream(filename));
                newOutput.writeObject(user);
                newOutput.close();
            }
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(filename));
            while (true) {
                try {
                    User u = (User) input.readObject();
                    if (u.getUsername().equals(userName)) {
                        u.setIcon(null);
                        contacts.add(u);
                        contactSaved = true;
                    } else {
                        contacts.add(u);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            input.close();

            if (!contactSaved) {
                User newContact = new User(userName, null);
                contacts.add(newContact);
            }

            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream(filename));
            for (User u : contacts) {
                output.writeObject(u);
                output.flush();
            }
            output.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        updateContactList();
    }
    /**
     *Koden innehåller en public metod, updateContactList(), som läser från en binärdatafil, Contacts.bin,
     * med en ObjectInputStream. För varje objekt som läses från filen lägger koden till objektet i en lista
     * av User objekt, savedUsers, och lägger till användarnamnet i en annan lista av String, contactsUserNames.
     * Metoden rensar savedUsers-listan innan den lägger till nya användare i den.
     */



    public ArrayList<String> updateContactList() {
        ArrayList<String> contactsUserNames = new ArrayList<>();
        savedUsers.clear();
        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream("users/" + this.user.getUsername() + ".bin"));
            while (true) {
                try {
                    User u = (User) input.readObject();
                    savedUsers.add(u);
                    contactsUserNames.add(u.getUsername());
                } catch (EOFException e) {
                    break;
                }
            }
            input.close();
        } catch (FileNotFoundException e){

        } catch (NullPointerException e){

        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactsUserNames;
    }
    /**
     Returnerar en ArrayList som innehåller användarnamnen för alla online-användare.
     @return ArrayList av användarnamn
     */
    public ArrayList<String> getOnlineUserNames(){
        ArrayList<String> onlineUserNames = new ArrayList<>();

        for (User u : onlineUsers){
            onlineUserNames.add(u.getUsername());
        }

        return onlineUserNames;
    }

    /**
     * /**
     *
     *     Lägger till en PropertyChangeListener i listan över lyssnare för att lyssna på ändringar i en observerad egenskap.
     *     @param l PropertyChangeListener att lägga till
     */
    public void addListener(PropertyChangeListener l){
        changeSupport.addPropertyChangeListener(l);
    }

    /**
     * En privat inre klass som ärver från klassen Thread. Klassen innehåller en metod med namnet run() som körs kontinuerligt
     * tills tråden stängs ner. Metoden läser in objekt från en ObjectInputStream och skickar PropertyChangeEvent meddelanden
     * till lyssnarna när ett meddelande eller en användarlista tas emot.
     */
    private class Receiver extends Thread{

        public void run(){
            try {
                while (true) {
                    Object o = inputStream.readObject();
                    if (o instanceof Message){
                        String m;
                        message = (Message) o;
                        message.setTimeClient(LocalDateTime.now().toString());
                        m = message.getTimeClient() + "         " + message.getClient().getUsername() + " till dig: " + message.getMeddelande() ;

                        if (message.getBild()!=null){
                            changeSupport.firePropertyChange("Picture", null, message.getBild());
                        }
                        changeSupport.firePropertyChange("Message",null,m);
                    }
                    else if (o instanceof ArrayList){
                        onlineUsers.clear();
                        onlineUsers = (ArrayList<User>) o;
                        changeSupport.firePropertyChange("User", null, onlineUsers);
                    }
                }
            } catch (EOFException e){

            } catch (SocketException e){

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
