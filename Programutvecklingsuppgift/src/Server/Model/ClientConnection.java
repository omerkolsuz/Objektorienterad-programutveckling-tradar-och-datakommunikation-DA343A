package Server.Model;

/**
 The ClientConnection class manages the online status of users by maintaining a mapping of users to their corresponding client communication objects.
 This class provides methods for adding, removing and retrieving user information, as well as obtaining a list of all currently online users.
 This class is intended for use in a multi-user chat application.
 @author Bashar Hassan
 */

import GlobalModel.User;
import Server.Controller.ClientCommunication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientConnection {
    /**

     Adds a user and their corresponding client communication object to the online user map.
     @param user The User object representing the user to be added.
     @param cc The ClientCommunication object representing the users client communication.
     */
    private HashMap<User, ClientCommunication> online = new HashMap<User,ClientCommunication>();
    public synchronized void put(User user, ClientCommunication cc){
        online.put(user,cc);
    }
    /**

     Removes the entry in the online user map corresponding to the provided client communication object.
     @param clientCommunication The ClientCommunication object to be removed.
     @return True if the entry was successfully removed false otherwise.
     */
    public synchronized boolean remove(ClientCommunication clientCommunication){
        boolean deleted = false;
        for (Map.Entry me : online.entrySet()){
            if(me.getValue()==clientCommunication){
                online.remove(me.getKey());
                System.out.println("Client deleted");
                deleted = true;
            }
        }
        return deleted;
    }
    /**
     ClientCommunication object corresponding to the provided user object from the online user map.
     @param user The User object representing the user whose ClientCommunication object is to be retrieved.
     @return The ClientCommunication object corresponding to the provided User object or null if no such object exists in the map.
     */
    public synchronized ClientCommunication getClientCommunication(User user){
        return online.get(user);
    }
    /**
     Determines whether the online user map contains an entry for the provided user object.
     @param user The User object representing the user to be checked.
     @return True if the online user map contains an entry for the provided user, false otherwise.
     */
    public synchronized boolean hittaUser(User user){
        return online.containsKey(user);
    }
    /**

     Retrieves a list of all User objects currently present in the online user map.
     @return An ArrayList containing all User objects currently present in the online user map.
     */
    public synchronized ArrayList<User> getOnlineListan(){
        ArrayList<User> onlineUsers = new ArrayList<User>();
        for (User u : online.keySet()){
            onlineUsers.add(u);
        }
        return onlineUsers;
    }
}
