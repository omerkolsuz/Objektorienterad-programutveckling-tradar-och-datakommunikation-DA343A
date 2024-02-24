package Server.View;
/**
 * here vi fixin gui of server start
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class ServerView extends JFrame implements ActionListener {
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel nWestPanel;
    private JPanel nEastPanel;
    private JTextArea historikText = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(historikText); // Add a JScrollPane for the JTextArea
    private DateFormat datumFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat tidFormat = new SimpleDateFormat("HH:mm:ss");
    private JFormattedTextField startDateText;
    private JFormattedTextField finishDateText;
    private JFormattedTextField startTimeText;
    private JFormattedTextField finishTimeText;
    private JLabel startDatelabel;
    private JLabel finishDatelabel;
    private JLabel startTimelabel;
    private JLabel finishTimelabel;
    private JLabel lblStart;
    private JLabel lblStartTime;
    private JLabel lblIpPort;
    private JButton btnSokHistorik = new JButton("Sök Historik");
    private JButton btnClearHistorik = new JButton("Rensa Historik");
    private Date date = new Date();



    public ServerView(InetAddress ip, int port) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println(ip + " : " + port);
        setPreferredSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        scrollPane.setPreferredSize(new Dimension(700, 350)); // Set the preferred size of the JScrollPane
        southPanel.add(scrollPane); // Add the JScrollPane to the center panel instead of the JTextArea

        // Add a welcome label to the north panel
        nWestPanel = new JPanel();
        nWestPanel.setLayout(new GridLayout(2,4));
        JLabel welcomeLabel = new JLabel("Välkommen Till bästa Chat Appen (MAU CHAT)");
        centerPanel.add(welcomeLabel);

        startDateText = new JFormattedTextField(datumFormat);
        finishDateText = new JFormattedTextField(datumFormat);
        startTimeText = new JFormattedTextField(tidFormat);
        finishTimeText = new JFormattedTextField(tidFormat);

        startDateText.setText("");
        finishDateText.setText("");
        startTimeText.setText("00:00:00");
        finishTimeText.setText("23:59:59");

        startDatelabel = new JLabel("Datum från:");
        startTimelabel = new JLabel("Tid från:");
        finishDatelabel = new JLabel("Datum till:");
        finishTimelabel = new JLabel("Tid till");

        nWestPanel.add(startDatelabel);
        nWestPanel.add(startDateText);
        nWestPanel.add(startTimelabel);
        nWestPanel.add(startTimeText);

        nWestPanel.add(finishDatelabel);
        nWestPanel.add(finishDateText);
        nWestPanel.add(finishTimelabel);
        nWestPanel.add(finishTimeText);

        JPanel picturePanel = new JPanel();
        picturePanel.setPreferredSize(new Dimension(600, 200)); // Set the preferred size of the picture panel

        centerPanel.add(picturePanel);

        // Load the picture and add it to the picture panel
        ImageIcon image = new ImageIcon("files/appLogo.png");
        JLabel pictureLabel = new JLabel(image);
        picturePanel.add(pictureLabel);

        nEastPanel = new JPanel();
        nEastPanel.setLayout(new GridLayout(2,2));
        lblStart = new JLabel("Server har startat: ");
        lblStartTime = new JLabel(date.toString());

        btnSokHistorik.addActionListener(this);
        btnClearHistorik.addActionListener(this);

        nEastPanel.add(lblStart);
        nEastPanel.add(lblStartTime);
        nEastPanel.add(btnSokHistorik);
        nEastPanel.add(btnClearHistorik);

        northPanel.setLayout(new GridLayout(1,2));
        northPanel.add(nWestPanel);
        northPanel.add(nEastPanel);

        lblIpPort = new JLabel("Host: " + ip + " ::: Server Port: " + port);
        centerPanel.add(lblIpPort);

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setLocationByPlatform(true);
        pack();
        setVisible(true);
    }

    /**
     * here where we making a scan of file ServerLog and putting this file to server gui.
     */
    public void scanFile() {
        ObjectInputStream inputStream;

        String fromTime = (startDateText.getText() + "T" + startTimeText.getText());
        LocalDateTime FromTime = LocalDateTime.parse(fromTime);
        String toTime= (finishDateText.getText() + "T" + finishTimeText.getText());
        LocalDateTime ToTime = LocalDateTime.parse(toTime);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("files/ServerLog.txt"),"UTF-8"))){
            String str = br.readLine();
            while (str!=null) {
                String[] str1 = str.split("-------------------", 0);
                String s = str1[0];
                try{
                    LocalDateTime currTime = LocalDateTime.parse(s);
                    if (currTime.isAfter(FromTime)&&currTime.isBefore(ToTime)){
                        historikText.append(str+"\n");
                        str = br.readLine();
                    }
                }catch (Exception e){
                    historikText.append(str+"\n");
                    str = br.readLine();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * här vi fixar vad som händer när clicker på button.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSokHistorik){
            scanFile();
        }
        else if (e.getSource() == btnClearHistorik){
            historikText.setText("");
        }

    }
}