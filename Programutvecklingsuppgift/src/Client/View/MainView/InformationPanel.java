package Client.View.MainView;
/**
 *
 *     The class represents a JPanel that displays information about contacts.
 *     It displays two JLists: one for the saved contacts, and one for the currently online contacts.
 *     It also has functionality for selecting users and saving contacts.
 *     Implements PropertyChangeListener to receive updates when user information changes.
 */


import Client.Controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class InformationPanel extends JPanel implements PropertyChangeListener {
    private MainFrame view;
    private ClientController controller;
    private ArrayList<String> SavedUserList = new ArrayList<>();
    private ArrayList<String> OnlineUserList = new ArrayList<>();
    private JList<String> savedList;
    private JList<String> Listonline;
    private RPanel rPanel;

    /**
     * Creates a new InformationPanel with the given MainFrame and ClientController.
     * Initializes JLists for saved and online contacts and adds a RPanel to the left.
     * Registers itself as a listener with the ClientController.
     *
     * @param view MainFrame object for this InformationPanel.
     * @param controller ClientController object for this InformationPanel.
     */

    public InformationPanel(MainFrame view, ClientController controller) {
        this.controller = controller;
        this.view = view;
        Listonline = new JList<>();
        savedList = new JList<>();
        controller.addListener(this);
        setLayout(new BorderLayout());

        rPanel = new RPanel(view,controller,this);
        add(rPanel,BorderLayout.WEST);

        SavedUserList = controller.updateContactList();
        setContacts();
    }

    /**
     * Clears and updates the saved and online contact lists based on information from the ClientController.
     */

    public void setContacts() {
        SavedUserList.clear();
        OnlineUserList.clear();
        SavedUserList = controller.updateContactList();
        OnlineUserList = controller.getOnlineUserNames();
        String[] usernames = new String[SavedUserList.size()];
        for (int i = 0; i < usernames.length; i++) {
            usernames[i] = SavedUserList.get(i);
        }
        savedList.setListData(usernames);

        String[] onlineUsers = new String[OnlineUserList.size()];
        int index = 0;
        for (String u : OnlineUserList) {
            onlineUsers[index] = u;
            index++;
        }
        Listonline.setListData(onlineUsers);
    }

    /**
     * Retrieves the selected online and saved users from their respective JLists and adds them to the MainFrame's target list.
     */


    public void selectedUsers() {
        int[] index = Listonline.getSelectedIndices();
        ArrayList<String> selectedUsers = new ArrayList<>();
        for (int i = 0; i < index.length; i++) {
            Object o = Listonline.getModel().getElementAt(index[i]);
            for (String onlineUser : OnlineUserList) {
                if (o.equals(onlineUser)) {
                    selectedUsers.add(onlineUser);
                }
            }
        }
        Listonline.removeSelectionInterval(0, Listonline.getMaxSelectionIndex());
        index = savedList.getSelectedIndices();
        for (int i = 0; i < index.length; i++) {
            Object o = savedList.getModel().getElementAt(index[i]);
            for (String savedUser : SavedUserList) {
                if (o.equals(savedUser) && !selectedUsers.contains(savedUser)) {
                    selectedUsers.add(savedUser);
                }
            }
        }
        savedList.removeSelectionInterval(0, savedList.getMaxSelectionIndex());
        view.setTargetList(selectedUsers);
    }

/**
 * Saves selected online contacts to the ClientController
 *
 **/

    public void saveContacts() {
        int[] selectedUsers = Listonline.getSelectedIndices();
        for (int i = 0; i < selectedUsers.length; i++) {
            String selectedUser = Listonline.getModel().getElementAt(selectedUsers[i]).toString();
            if (SavedUserList.contains(selectedUser)) {
                continue; // skip if the user is already saved
            } else {
                controller.saveContact(selectedUser);
                SavedUserList.add(selectedUser); // add the new contact to the savedUserList
            }
        }
        setContacts();
    }

    /**
     * tar emot kontakt listan
     * @return
     */
    public JList<String> getSavedList() {
        return savedList;
    }

    /**
     * tar emot online listan
     * @return
     */
    public JList<String> getListonline() {
        return Listonline;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Users")) {
            setContacts();
        }
    }
}