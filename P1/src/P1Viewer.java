package src;
/**
 * . Klassen ärver från en annan klass som heter Viewer och implementerar interfacet
 * PropertyChangeListener.
 * @author Ömer
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class P1Viewer extends Viewer implements PropertyChangeListener{


    /**
     * Klassen har en konstruktor som tar en MessageManager och två ints,
     * bredden och höjden, som argument. Konstruktorn lägger till en PropertyChangeListener
     * till messageManager genom att anropa messageManager.addMessageListener(this)
     * @param messageManager
     * @param width
     * @param height
     */

    public P1Viewer(MessageManager messageManager, int width, int height) {
        super(width, height);
        messageManager.addMessageListener(this);
/**
 * Klassen har också en metod setMessage som sätter ett meddelande och ärver från sin superklass Viewer.
 */
    }
    public void setMessage(Message message1){
        super.setMessage(message1);
    }

    /**
     * Klassen implementerar även metoden propertyChange från interfacet PropertyChangeListener.
     * Metoden tar en PropertyChangeEvent som argument. Metoden kontrollerar om egenskapens namn är
     * "message". Om det är så, så tar den värdet för det nya värdet och castar det till ett
     * Message-objekt. Därefter sätter den det nya värdet som ett meddelande genom att anropa setMessage
     * med det nya värdet som argument.
     * @param changeEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent changeEvent) {
        if (changeEvent.getPropertyName().equals("message")){
            Message msg = (Message)changeEvent.getNewValue();
            setMessage(msg);
        }

    }
}
