package p1;

/**
 * Det här är en Java-klass för ett objekt med namnet "Message".
 * Klassen implementerar gränssnittet Serializable, vilket betyder att objekten av
 * denna klass kan serialiseras och deserialiseras, det vill säga konverteras till och
 * från en ström av bytes för att kunna överföras eller lagras.
 * Konstruktorn Message tar två argument, en sträng för textmeddelandet och en ikon,
 * och används för att skapa ett nytt Message-objekt.
 *
 * @author Ömer Kolsuz
 */

import javax.swing.*;
import java.io.Serializable;

/**
 * Klassen har två medlemsvariabler, message och icon,
 * som representerar textmeddelandet och en ikon som ska visas tillsammans med textmeddelandet.
 */
public class Message implements Serializable {
    private String text;
    private Icon icon;

    public Message(String message, Icon icon) {
        this.text = message;
        this.icon = icon;
    }

    /**
     * Klassen har också två metoder,
     * en för att hämta textmeddelandet (getText)
     * och en för att hämta ikonen (getIcon).
     *
     * @return
     */
    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }
}
