package GlobalModel;
/**
 * A class representing a user of the chat application.
 * @author Hossein Khavari
 */

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable {
    private String username;
    private ImageIcon image;

    /**
     * Creates a new user with the given username and profile image.
     *
     * @param username the username of the user
     * @param image the profile image of the user
     */
    public User(String username, ImageIcon image) {
        this.username = username;
        this.image = image;
    }

    public User() {

    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the user.
     *
     * @param username the new username for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the hash code for the user, which is based on their username.
     *
     * @return the hash code for the user
     */
    @Override
    public int hashCode(){
        return username.hashCode();
    }

    /**
     * Compares this user to another object for equality. The user is considered equal
     * to another object if that object is also a user and has the same username.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */

    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof User){
            return username.equals(((User) o).getUsername());
        }
        return false;
    }

    public void setIcon(ImageIcon image) {
        this.image = image;

    }
}
