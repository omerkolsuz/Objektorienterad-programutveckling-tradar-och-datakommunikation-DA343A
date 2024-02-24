package Client.View.MainView;
/**

 The LPanel class represents the left panel of the main view of the chat client.
 It contains the chat history, message input field, send message button, and select image button.
 */

import Client.Controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LPanel extends JPanel implements ActionListener {
    private ClientController controller;
    private MainFrame view;
    private DefaultListModel<Object> listModel = new DefaultListModel<>();
    private JList<Object> messageList;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField messageField;

    /**
     * Constructs a new LPanel with the specified MainFrame and ClientController.
     * @param view the MainFrame that contains this LPanel.
     * @param controller the ClientController that controls this LPanel.
     */

    public LPanel(MainFrame view, ClientController controller) {
        this.view = view;
        this.controller = controller;
        setLayout(new BorderLayout());
        setPreferredSize(new java.awt.Dimension(500, 600));

        setUp();
    }
    /**
     * Sets up the components of this LPanel, including the chat history list, message input field, send message button,
     * and select image button.
     */
    public void setUp() {
        messageList = new JList(listModel);
        jScrollPane1 = new javax.swing.JScrollPane(messageList);

        messageField = new JTextField();
        messageField.setText("Skriv ditt meddelande här!");
        messageField.setPreferredSize(new Dimension(300, 100));
        add(messageField);

        btnSend = new JButton("Skicka Meddelande");
        btnSend.addActionListener(this);
        add(btnSend);

        btnImage = new JButton("Välj Bild");
        btnImage.addActionListener(this);
        add(btnImage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addComponent(messageField)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(btnImage, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnImage, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                        .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
        );
    }

    /**
     * Allows the user to select an image file from their computer.
     * @return the absolute path of the selected image file.
     */

    public String selectImage() {
        String Path = "";
        JFileChooser chooser = new JFileChooser("files");
        int file = chooser.showOpenDialog(null);
        if (file == JFileChooser.APPROVE_OPTION) ;
        {
            Path = chooser.getSelectedFile().getAbsolutePath();
        }
        return Path;
    }

/**
 * Performs an action in response to a user clicking the send message or select image button.
 * If the send message button is clicked, sends the message to the selected recipients and appends it to the chat history.
 * If the select image button is clicked, sets the selected image as the current
**/

 @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSend) {
            if (view.getTargetList().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Välj en mottagare", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                controller.sendMessage(messageField.getText(), view.getTargetList(), view.getImage());
                view.setImage(null);

                append("Från dig till" + view.getTargetList() + ": " + messageField.getText());
                messageField.setText("");
            }
        } else if (e.getSource() == btnImage) {
            view.setImage(new ImageIcon(selectImage()));
        }
    }

    /**
     * det gör att vi lägger meddelandet listan till left panel
     * @param message
     */
    public void append(String message) {
        listModel.addElement(message);
        listModel.addElement("\n");
    }

    /**
     * det gör att vi lägger icon listan till left panel
     * @param icon
     */

    public void appendPicture(ImageIcon icon) {
        listModel.addElement(icon);
    }

}