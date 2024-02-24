package Client.View.LoginView;
/**
 * This class represents the graphical user interface for the login functionality.
 * It provides the user with fields for inputting their username, IP address and port number.
 * Users can also select a profile picture.
 */

import Client.Controller.ClientController;
import Client.View.MainView.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 *  Initializes the components of the LoginView object
 *
 */
public class Login extends JFrame implements ActionListener {
    private javax.swing.JButton btnDisconnect;
    private javax.swing.JButton btnLoggaIn;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JTextField ipTextField;
    private javax.swing.JButton btnValBild;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelWelcom;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameTextField;
    private JLabel nameLabel;
    private JTextField userName;
    private JButton selectPictureButton;
    private JButton loginButton;
    private String selectedImagePath = "";
    private JFileChooser chooser;
    private ClientController controller;
    private MainFrame mainFrame;


    public Login() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        initComponents();
    }

    private void initComponents() {

        jFrame1 = new JFrame();
        btnLoggaIn = new JButton();
        btnDisconnect = new JButton();
        labelWelcom = new JLabel();
        userNameLabel = new JLabel();
        userNameTextField = new JTextField();
        ipLabel = new JLabel();
        ipTextField = new JTextField();
        portLabel = new JLabel();
        portTextField = new JTextField();
        btnValBild = new JButton();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();

        GroupLayout jFrame1Layout = new GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
                jFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
                jFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));


        btnDisconnect.setText("Disconnect");
        btnDisconnect.addActionListener(this);


        labelWelcom.setFont(new Font("Herculanum", 0, 18)); // NOI18N
        labelWelcom.setText("Vällkommen till Bästa Chatt Appen (MAU CHATT)");


        btnLoggaIn = new JButton("Logga in");
        btnLoggaIn.addActionListener(this);
        add(btnLoggaIn);

        userNameLabel.setText("Användare Namn");
        add(userNameLabel);
        userNameTextField.setText("Mats007");



        ipLabel.setText("Ip Adress");
        ipTextField.setText("127.0.0.01");

        portLabel.setText("Port Nummer");
        portTextField.setText("2343");

        btnValBild.setText("Välj Bild");
        btnValBild.addActionListener(this);
        add(btnValBild);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Creators:\nÖmer Kolsuz\nOscar Svantesson\nBashar Hassan\nHossein Khavari\nAdam Lahbil");
        jScrollPane1.setViewportView(jTextArea1);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new ImageIcon("files/appLogo.png"));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(portLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(portTextField, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(userNameLabel)
                                                        .addComponent(ipLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(ipTextField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(userNameTextField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))))
                                .addGap(36, 36, 36)
                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnValBild, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105)
                                .addComponent(btnLoggaIn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDisconnect, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(79, Short.MAX_VALUE)
                                .addComponent(labelWelcom)
                                .addGap(140, 140, 140))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(100, Short.MAX_VALUE)
                                .addComponent(labelWelcom, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(129, 129, 129))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(userNameLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                .addGap(29, 29, 29)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(ipLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ipTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(portLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(portTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(97, 97, 97)
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                                                .addGap(34, 34, 34)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnValBild, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLoggaIn, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDisconnect, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }

    /**
     * här vi bästemmer vad händer när man klicker på en button
     * @param e the event to be processed
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoggaIn) {
            String name = userNameTextField.getText();
            String imagePath = selectedImagePath;

            if (name.equals("Mats007") || imagePath.equals("")){
                JOptionPane.showMessageDialog(null, "Användarnamn och bild måste fyllas i","Error", JOptionPane.WARNING_MESSAGE);
            } else {
                try{
                    if(new ClientController("127.0.0.1",2343,name).connect(name, imagePath));
                    dispose();
                } catch (Exception r){
                    r.printStackTrace();
                }
            }
        } else if (e.getSource() == btnValBild) {
            if (chooser == null){
                chooser = new JFileChooser("files");
            }
            chooser.showOpenDialog(null);
            selectedImagePath = chooser.getSelectedFile().getAbsolutePath();
        } else if (e.getSource() == btnValBild) {
            if (chooser == null) {
                    chooser = new JFileChooser("files");
            }
                chooser.showOpenDialog(null);
                selectedImagePath = chooser.getSelectedFile().getAbsolutePath();
        } else if (e.getSource() == btnDisconnect) {
            System.exit(0);
        }
    }

}