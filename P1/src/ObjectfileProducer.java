package src;
/**
 * Klassen implementerar gränssnittet MessageProducer. Klassen är en
 * producent av meddelanden från en fil.Klassen har 3 variabler
 * @param delay: en int-variabel som innehåller den fördröjning som ska
 * tillämpas mellan varje produktion av ett meddelande.
 * @param times : en int-variabel som innehåller antalet gånger en producent
 * ska producera ett meddelande.
 * @param size : en en int-variabel som innehåller storleken på den samling
 * av meddelanden som producenten ska producera.
 * @param s: en strängvariabel som innehåller filnamnet på den fil från
 * vilken producenten hämtar meddelanden.
 * @param aktuellindex: en int-variabel som håller reda på det aktuella indexet
 * ör den samling av meddelanden som producenten producerar.
 */

import java.io.*;

public class ObjectfileProducer implements MessageProducer {

    private int delay;
    private int times;
    private int size;
    private String s;
    private int aktuellindex = -1;

    /**
     * Konstruktorn ObjectfileProducer tar ett argument,
     * filnamnet, och används för att skapa ett nytt ObjectfileProducer-objekt.
     * @param s
     */
    public ObjectfileProducer(String s){
        this.s = s;
    }

    /**
     * Metoden delay() returnerar värdet av fördröjningen. Metoden öppnar en ObjectInputStream
     * från filen och läser in två värden, det första och det andra, och returnerar det andra
     * värdet som fördröjning.
     * @return
     */
    @Override
    public int delay() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(s))) {
            inputStream.readInt();
            delay = inputStream.readInt();

        } catch (IOException e) {
        }
        return delay;
    }

    /**
     * Metoden times() returnerar värdet av antalet gånger en producent ska producera ett meddelande.
     * Metoden öppnar en ObjectInputStream från filen och läser in ett värde och returnerar det värdet
     * som antalet gånger.
     * @return
     */
    @Override
    public int times() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(s))) {
            times = inputStream.readInt();

        } catch (IOException e) {
        }
        return times;
    }

    /**
     * Metoden size() returnerar storleken på den samling av meddelanden som producenten ska
     * producera. Metoden öppnar en ObjectInputStream från filen, läser in tre värden, och
     * returnerar det tredje värdet som storleken.
     * @return
     */

    @Override
    public int size() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(s))){
            inputStream.readInt();
            inputStream.readInt();
            size = inputStream.readInt();

        } catch (IOException e) {
        }
        return size;
    }

    /**
     * Metoden nextMessage() returnerar det nästa meddelandet i samlingen av meddelanden som
     * producenten producerar. Metoden öppnar en ObjectInputStream från filen, läser in tre
     * värden och en samling av Message-objekt, och returnerar ett Message-objekt från samlingen.
     * aktuellindex håller reda på vilket index i samlingen som returneras nästa gång.
     * @return
     */
    @Override
    public Message nextMessage() {
        Message[] messages = new Message[size];
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(s))) {
            inputStream.readInt();
            inputStream.readInt();
            inputStream.readInt();
            for (int i = 0; i<size; i++){
                messages[i] = (Message)inputStream.readObject();
            }
        } catch (IOException e) {} catch (ClassNotFoundException e) {}
        if(size()==0)
            return null;
        aktuellindex = (aktuellindex +1) % messages.length;
        return messages[aktuellindex];
    }
}
