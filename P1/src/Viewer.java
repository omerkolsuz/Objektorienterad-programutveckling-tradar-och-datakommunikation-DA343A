package src;
/**
 * Klassen ärver från JPanel-klassen i Java Swing-biblioteket.
 * Klassen definierar två JLabel-fält, labelText och labelIcon.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Viewer extends JPanel {
    private JLabel labelText;
    private JLabel labelIcon;

    /**
     * konstruktor som tar två argument, bredd och höjd, för att skapa en Viewer-panel.
     * Inuti konstruktorn används SwingUtilities.invokeLater() för att ställa in layouten
     * till BorderLayout, skapa två JLabel-objekt, ställa in deras font och opaqueness,
     * och lägga till dem till panelen.konstruktor som tar två argument, bredd och höjd,
     * för att skapa en Viewer-panel. Inuti konstruktorn används SwingUtilities.invokeLater()
     * för att ställa in layouten till BorderLayout, skapa två JLabel-objekt, ställa in deras
     * font och opaqueness, och lägga till dem till panelen.
     * @param width
     * @param height
     */
    public Viewer(int width, int height) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setLayout(new BorderLayout());
                labelText = new JLabel(" ",JLabel.CENTER);
                labelIcon = new JLabel(" ",JLabel.CENTER);
                labelText.setFont(new Font("SansSerif",Font.PLAIN,20));
                labelIcon.setOpaque(true);
                add(labelText,BorderLayout.NORTH);
                add(labelIcon);
                labelIcon.setPreferredSize(new Dimension(width,height));
            }
        });
    }

    /**
     * Metoden setMessage() tar ett Message-objekt som argument och använder
     * också SwingUtilities.invokeLater() för att ställa in text och ikon på JLabel-objekten.
     * @param message
     */

    public void setMessage(Message message) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                labelText.setText(message.getMessage());
                labelIcon.setIcon(message.getIcon());
            }
        });
    }

    /**
     * Metoden showPanelInFrame() tar ett JPanel-objekt, en sträng för titeln,
     * och två heltal x och y för att visa panelen i en JFrame. Allt händer inuti
     * SwingUtilities.invokeLater() för att se till att allt kör i swing event
     * dispatch thread (EDT), som är den tråd som hanterar all grafisk presentation i swing.
     * @param panel
     * @param title
     * @param x
     * @param y
     */
    public static void showPanelInFrame(final JPanel panel, String title, int x, int y) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(title);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.pack();
                frame.setLocation(x, y);
                frame.setVisible(true);
            }
        });
    }
}

