package p1;

import p1.Buffer;
import p1.MessageProducer;

/**
 * Klassen används för att lägga till MessageProducer-objekt i en Buffer.
 * Klassen har två medlemsvariabler,
 * producerBuffer är en referens till en Buffer-objekt av typen MessageProducer,
 * som håller producenter av meddelanden.
 *
 * @author Ömer Kolsuz
 */

public class MessageProducerInput {
    private Buffer<MessageProducer> producerBuffer;

    /**
     * Konstruktorn MessageProducerInput tar ett argument, en referens till en Buffer-objekt av typen
     * MessageProducer, och används för att skapa ett nytt MessageProducerInput-objekt.
     *
     * @param messageProducerBuffer
     */
    public MessageProducerInput(Buffer<MessageProducer> messageProducerBuffer) {
        this.producerBuffer = messageProducerBuffer;
    }

    /**
     * Metoden addMessageProducer tar ett argument, ett MessageProducer-objekt, och lägger till
     * det i producerBuffer med hjälp av metoden put.
     *
     * @param messProducer
     */
    public void addMessageProducer(MessageProducer messProducer) {
        producerBuffer.put(messProducer);
    }
}
