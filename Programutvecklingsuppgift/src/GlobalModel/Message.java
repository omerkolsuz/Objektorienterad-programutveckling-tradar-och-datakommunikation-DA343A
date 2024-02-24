package GlobalModel;
/**
 * The Message class represents a message that can be sent between users in the application.
 *
 * @author Bashar Hassan
 */

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Message implements Serializable {
    private String meddelande;
    private ImageIcon bild;
    private User client;
    private ArrayList<User> receivers;
    private String timeFromServer;
    private String timeFromClient;
    private boolean logout = false;

    /**
     * Creates a new `Message` instance with the specified content, sender, and recipients.
     *
     * @param meddelande The message content.
     * @param client The user who sent the message.
     * @param receivers The list of users who should receive the message.
     */

    public Message(String meddelande, User client, ArrayList<User> receivers) {
        this.meddelande = meddelande;
        this.client = client;
        this.receivers = receivers;
        this.bild = null;
    }
    /**
     * Creates a new `Message` instance with the specified content, sender, recipients, and image.
     *
     * @param meddelande The message content.
     * @param client The user who sent the message.
     * @param receivers The list of users who should receive the message.
     * @param bild An optional image attached to the message.
     */

    public Message(String meddelande, User client, ArrayList<User> receivers, ImageIcon bild) {
        this.meddelande = meddelande;
        this.bild = bild;
        this.client = client;
        this.receivers = receivers;
    }
    /**
     * Creates a new `Message` instance indicating that the user is logging out.
     *
     * @param logout Whether or not the user is logging out.
     * @param client The user who is logging out.
     */
    public Message(boolean logout, User client) {
        this.client = client;
        this.logout = logout;
    }
    /**
     * Gets the message content.
     *
     * @return The message content.
     */
    public String getMeddelande() {
        return meddelande;
    }
    /**
     * Gets the image attached to the message.
     *
     * @return The image attached to the message, or `null` if there is no image.
     */
    public ImageIcon getBild() {
        return bild;
    }
    /**
     * Gets the user who sent the message.
     *
     * @return The user who sent the message.
     */
    public User getClient() {
        return client;
    }
    /**
     * Gets the list of users who should receive the message.
     *
     * @return The list of users who should receive the message.
     */
    public ArrayList<User> getReceivers() {
        return receivers;
    }
    /**
     * Sets the time the message was received by the server to the current time.
     */
    public void setTimeServer() {
        this.timeFromServer = LocalDateTime.now().toString();
    }
/**
 * Gets the time the message was sent by the client.
 *
 **/

 public String getTimeClient() {
        return timeFromClient;
    }
    /**
     * set the time the message was sent by the client.
     *
     **/
    
    public void setTimeClient(String time) {
        this.timeFromClient = time;
    }

    /**
     Returns the boolean value indicating whether the message is a logout message or not.
     @return the value of the boolean variable logout
     */
    public boolean getLogout() {
        return logout;
    }
}
