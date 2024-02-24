package Client.View.MainView;

/**

 The MainFrame class represents the main window of the chat application. It contains an InformationPanel, where
 the user can search for other users to chat with, and an LPanel, where the user can send messages and select
 images to send to the selected recipients.
 */

import Client.Controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements PropertyChangeListener {

    private ClientController controller;
    private ArrayList<String> targetList;
    private ImageIcon image;
    private LPanel lPanel;
    private InformationPanel infoPanel;

    /**
     * Constructs a MainFrame with a given ClientController and window name.
     *
     * @param controller the ClientController for the chat application
     * @param name the name of the window
     */

    public MainFrame(ClientController controller, String name){
        this.controller=controller;
        targetList = new ArrayList<>();
        setPreferredSize(new Dimension(1000,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        controller.addListener(this);
        setTitle(name);
        pack();
        setUp();
    }
    /**
     * Sets up the layout of the MainFrame. Adds an InformationPanel to the EAST and an LPanel to the CENTER.
     */

    public void setUp(){

        infoPanel = new InformationPanel(this,controller);
        add(infoPanel,BorderLayout.EAST);

        lPanel = new LPanel(this, controller);
        add(lPanel);

        pack();
        setVisible(true);
    }
    /**
     * Returns the currently selected image to be sent.
     *
     * @return the selected image
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * Sets the selected image to be sent.
     *
     * @param image the image to be sent
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    /**
     * Returns the list of users that have been selected as recipients for a message.
     *
     * @return the list of selected recipients
     */
    public ArrayList<String> getTargetList() {
        return targetList;
    }

    /**
     * Sets the list of users that have been selected as recipients for a message.
     *
     * @param users the list of selected recipients
     */
    public void setTargetList(ArrayList<String> users) {
        targetList.clear();
        this.targetList = users;
    }
    /**
     * Updates the MainFrame when a property change event occurs.
     *
     * @param evt the PropertyChangeEvent that occurred
     */

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Message")){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    lPanel.append(evt.getNewValue().toString());
                }
            });

        } else if (evt.getPropertyName().equals("Picture")) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    lPanel.appendPicture((ImageIcon) evt.getNewValue());
                }
            });
        }
    }
}
