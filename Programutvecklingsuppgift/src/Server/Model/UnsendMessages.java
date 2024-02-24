package Server.Model;

import GlobalModel.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The UnsendMessages class maintains a mapping of users to lists of unsent messages. This class provides methods for adding and retrieving unsent messages
 * for individual users, as well as checking if a user has any unsent messages pending.
 * This class is intended for use in a messaging application.
 * @author Hossein Khavari
 **/

/**
 * @author
 * @version
 * @date
 */
public class UnsendMessages {
    public synchronized void put(User user, Message message){
        if(findUser(user)){
            findList(user).add(message);
        }
        else {
            ArrayList<Message> list = new ArrayList<Message>();
            pending.put(user, list);
            findList(user).add(message);
        }
    }

    /**
     Retrieves the list of unsent messages for the provided user.
     @param user The User object representing the user whose unsent message list is to be retrieved.
     @return An ArrayList containing all unsent messages for the provided user, or null if no such list exists in the map.
     */
    public synchronized ArrayList<Message> findList(User user){
        return pending.get(user);
    }
    /**
     Retrieves and removes the list of unsent messages for the provided user from the map.
     @param user The User object representing the user whose unsent message list is to be retrieved and removed.
     @return An ArrayList containing all unsent messages for the provided user, or null if no such list exists in the map.
     */
    public synchronized ArrayList<Message> getMList(User user) {
        return pending.remove(user);
    }
    /**
     Determines whether the unsent message map contains an entry for the provided user object.
     @param user The User object representing the user to be checked.
     @return True if the unsent message map contains an entry for the provided user, false otherwise.
     */
    public synchronized boolean findUser(User user){
        return pending.containsKey(user);
    }

    /**
     * The pending member variable is a HashMap object that maps User objects to ArrayLists of Message objects.
     * This HashMap is used to maintain a mapping of users to lists of unsent messages in the UnsentMessages class.
     */
    private HashMap<User, ArrayList<Message>> pending = new HashMap<User, ArrayList<Message>>();
}