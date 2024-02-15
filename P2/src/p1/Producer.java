package p1;

import p1.Buffer;
import p1.Message;
import p1.MessageProducer;

/**
 * Klassen Producer är en tråd som representerar en producent.
 * Denna klass har som syfte att hantera implementeringar av MessageProducer och att placera dem.
 * Det finns två buffer-objekt som är instansierade i denna klass, messageProducerBuffer och bufferMessage.
 *
 * @author Ömer
 */
public class Producer extends Thread {
    private Buffer<MessageProducer> messageProducerBuffer;
    private Buffer<Message> bufferMessage;

    /**
     * Konstruktorn för klassen Producer
     *
     * @param prodBuffer    : objekt
     * @param bufferMessage : objekt
     */
    public Producer(Buffer<MessageProducer> prodBuffer, Buffer<Message> bufferMessage) {
        this.messageProducerBuffer = prodBuffer;
        this.bufferMessage = bufferMessage;
    }

    /**
     * Startmetoden start() anropar super.start() för att starta tråden.
     */
    @Override
    public synchronized void start() {
        super.start();
    }

    /**
     * Metoden run() är där den faktiska produktionen av meddelanden sker.
     * En loop körs så länge tråden inte avbryts (med Thread.interrupted()).
     * I loopen hämtas en MessageProducer från producerBuffer och antalet gånger och
     * hastigheten för varje produktion tas från MessageProducer. För varje iteration
     * av loopen hämtas ett nytt Message från MessageProducer och läggs till i messageBuffer.
     * Tråden väntar sedan en viss tid baserad på hastigheten i MessageProducer
     * (med Thread.sleep(mp.delay())).
     */
    public void run() {
        MessageProducer adamahmad;
        while (!Thread.interrupted()) {
            try {
                adamahmad = messageProducerBuffer.get();
                Message message;
                for (int times = 0; times < adamahmad.times(); times++) {
                    for (int index = 0; index < adamahmad.size(); index++) {
                        message = adamahmad.nextMessage();
                        bufferMessage.put(message);
                        Thread.sleep(adamahmad.delay());
                    }
                }
            } catch (InterruptedException e) {
            }
        }
    }
}

