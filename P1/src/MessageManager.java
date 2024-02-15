package src;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Klassen implementerar gränssnittet Runnable och används för att hantera
 * och skriva ut Message-objekt från en Buffer. Klassen har tre medlemsvariabler:
 * bufferMessage är en referens till en Buffer-objekt av typen Message, som håller meddelanden.
 * thread är en Thread-objekt som används för att köra run-metoden.
 * changeSupport är en instans av PropertyChangeSupport, som används för att underrätta intressenter om förändringar
 * i Message-objekt.
 */
public class MessageManager implements Runnable {
    private Buffer<Message> bufferMessage;
    private Thread thread;
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    /**
     * Metoden addMessageListener lägger till en PropertyChangeListener till changeSupport-objektet,
     * så att denna listener kan bli underrättad om förändringar.
     * @param listener
     */
    public void addMessageListener(PropertyChangeListener listener){
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     *Konstruktorn MessageManager tar ett argument, en referens till en Buffer-objekt av typen Message,
     * och används för att skapa ett nytt MessageManager-objekt.
     * @param bufferMessage
     */
    public MessageManager(Buffer<Message> bufferMessage) {
        this.bufferMessage = bufferMessage;
    }

    /**
     * Metoden start skapar och startar en ny tråd om thread-objektet inte redan finns.
     */
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Metoden run är den metod som körs när tråden startar och hämtar meddelanden från messageBuffer
     * och skriver ut textmeddelandet. Varje gång ett nytt meddelande hämtas skickas en förändring till
     * listeners genom att anropa change.firePropertyChange med argumenten "message", false, message,
     * där message är det hämtade Message-objektet.
     */

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Message message = bufferMessage.get();
                System.out.println(message.getMessage());
                changeSupport.firePropertyChange("message", false, message);

            } catch (InterruptedException e) {}
        }
    }
}

