package Client.View.MainView;
/**

 The RPanel class represents the right panel in the MainView of the client chat application.
 It displays the online users and the saved contacts of the current user, as well as provides
 options to select a user, save a contact, and disconnect from the server.
 */
import Client.Controller.ClientController;
import Client.View.LoginView.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RPanel extends JPanel implements ActionListener {

    private InformationPanel infoPanel;
    private JList<String> kontaktListanString;
    private JList<String> onlineListanString;
    private JButton btnSparaKontakt;
    private JButton btnValjKontakt;
    private ClientController controller;
    private JButton btnDisconnect;
    private MainFrame mainFrame;
    private JLabel onlineListLabel;
    private JLabel kontaktListLabel;
    private JScrollPane onlineListScrollPane;
    private JScrollPane kontaktListScrollPane;
    /**
     * Constructs a new RPanel with the given MainFrame, ClientController, and InformationPanel.
     *
     * @param mainFrame    the main frame of the client application
     * @param controller   the controller for the client
     * @param infoPanel    the information panel of the client application
     */

    public RPanel(MainFrame mainFrame, ClientController controller, InformationPanel infoPanel) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        this.infoPanel = infoPanel;

        setLayout(new BorderLayout());
        btnValjKontakt = new javax.swing.JButton();
        btnSparaKontakt = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        onlineListScrollPane = new javax.swing.JScrollPane();
        onlineListanString = new javax.swing.JList<>();
        kontaktListScrollPane = new javax.swing.JScrollPane();
        kontaktListanString = new javax.swing.JList<>();
        onlineListLabel = new javax.swing.JLabel();
        kontaktListLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(500, 600));

        btnValjKontakt.setText("Välj Kontakt");
        btnValjKontakt = new JButton("Välj Kontakt");
        btnValjKontakt.addActionListener(this);
        add(btnValjKontakt);


        btnSparaKontakt.setText("Spara Kontakt");
        btnSparaKontakt.addActionListener(this);
        add(btnSparaKontakt);

        btnDisconnect.setText("Disconnect");
        btnDisconnect.addActionListener(this);
        add(btnDisconnect);

        onlineListanString = infoPanel.getListonline();
        onlineListScrollPane.setViewportView(onlineListanString);

        kontaktListanString = infoPanel.getSavedList();
        kontaktListScrollPane = new JScrollPane(kontaktListanString);


        onlineListLabel.setText("Online Listan");

        kontaktListLabel.setText("Mina Kontakter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(onlineListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kontaktListScrollPane))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(onlineListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(24, 24, 24)
                                                .addComponent(kontaktListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 11, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnValjKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSparaKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(onlineListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(kontaktListLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(onlineListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                        .addComponent(kontaktListScrollPane))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnValjKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSparaKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDisconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

    }

    /**
     * här vi fixar vad som händer när man klicker på en button
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSparaKontakt) {
            infoPanel.saveContacts();
        } else if (e.getSource() == btnValjKontakt) {
            infoPanel.selectedUsers();
        }
        else if (e.getSource() == btnDisconnect) {
            controller.disconnect();
            new Login();
            mainFrame.dispose();
        }
    }
}