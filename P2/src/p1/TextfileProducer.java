package p1;
/**
 * Denna klass "TextfileProducer" implementerar MessageProducer-gränssnittet och används
 * för att hämta information från en textfil. Textfilen innehåller information om fördröjning,
 * antal gånger att köra, storleken på meddelanden och själva innehållet i meddelanden (text och ikoner).
 *
 * @author Ömer
 * @param s : används för att spara filnamnet
 * @param delay : används för att spara fördröjningen på den data som läses från filen.
 * @param times : används för att antalet gånger på den data som läses från filen.
 * @param size : används för att storleken på den data som läses från filen.
 * @param aktuellindex : är också en privat instansvariabel som används för att hålla
 * reda på nuvarande position i filen
 */

import p1.Message;
import p1.MessageProducer;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class TextfileProducer implements MessageProducer {

    private String s;
    private int delay;
    private int times;
    private int size;
    private int aktuellindex = -1;

    /**
     * Konstruktorn tar ett filnamn som argument och sparar det i instansvariabeln "s"
     * @param s
     */
    public TextfileProducer(String s) {
        this.s = s;
    }

    /**
     * Metoden delay() i TextfileProducer-klassen returnerar fördröjningsvärdet (delay)
     * som läses från en textfil med namn s. För detta används en BufferedReader
     * som öppnar och läser textfilen med hjälp av FileInputStream och InputStreamReader.
     * Först läses första raden i filen som ignoreras och sedan läses andra raden som är en sträng
     * som representerar fördröjningsvärdet. Denna sträng parsas till en int med hjälp av
     * Integer.parseInt(s1) och tilldelas fältet delay.
     * @return
     */
    @Override
    public int delay() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(s), "UTF-8"))) {
            reader.readLine();
            String s1 = reader.readLine();
            this.delay = Integer.parseInt(s1);
        } catch (IOException e) {
        }
        return delay;
    }

    /**
     * Metoden times() använder en try-with-resources-sats för att öppna en fil med namnet s och
     * läsa in dess första rad med hjälp av en BufferedReader. Raden läses in som en sträng s2,
     * som sedan parsas till ett heltal times med hjälp av metoden Integer.parseInt().
     * Slutligen returneras times.
     * @return
     */
    @Override
    public int times() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(s), "UTF-8"))) {
            String s2 = reader.readLine();
            this.times = Integer.parseInt(s2);
        } catch (IOException e) {
        }
        return times;
    }

    /**
     * Metoden size() i klassen TextfileProducer läser in storleken av en textfil.
     * Denna metod öppnar en BufferedReader som är ansluten till filen med namnet s
     * med teckenkodning "UTF-8". Metoden läser sedan de första tre raderna i filen
     * och den tredje raden (String s3) tolkas som en sträng representation av storleken
     * på textfilen. Denna sträng konverteras sedan till ett heltal med Integer.parseInt(s3)
     * och tilldelas size-variabeln. Slutligen returneras värdet på size.
     * @return
     */
    @Override
    public int size() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(s), "UTF-8"))) {
            reader.readLine();
            reader.readLine();
            String s3 = reader.readLine();
            this.size = Integer.parseInt(s3);
        } catch (IOException e) {
        }
        return size;
    }

    /**
     * Metoden i Java läser en fil, skapar en array av "Message"-objekt och returnerar nästa meddelande
     * i arrayen på ett cycliskt sätt. Metoden är definierad inom en klass och är annoterad med
     * "@Override"-annotationen, vilket indikerar att den är avsedd att skriva över en metod som
     * definieras i en superklass. Metoden börjar med att skapa en array av "Message"-objekt med
     * storleken på det aktuella objektet. Därefter används en try-catch-block för att läsa från
     * en fil med hjälp av en BufferedReader, dekodera innehållet med hjälp av en InputStreamReader
     * med UTF-8-kodning och spara resultatet i "meddelanden"-arrayen. Om ett IOException kastas
     * vid läsning av filen, fångas och ignoreras det. Till sist returnerar metoden nästa meddelande
     * i arrayen genom att öka en "aktuellindex"-variabel och ta modulo av längden på "meddelanden"
     * -arrayen. Om storleken på objektet är 0, returnerar metoden "null".
     * @return
     */
    @Override
    public Message nextMessage() {
        Message[] messages = new Message[size()];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(s), "UTF-8"))) {
            reader.readLine();
            reader.readLine();
            reader.readLine();
            for (int i = 0; i < size(); i++) {
                String text = reader.readLine();
                Icon icon = new ImageIcon(reader.readLine());
                messages[i] = new Message(text, icon);
            }

        } catch (IOException e) {
        }
        if (size() == 0)
            return null;
        aktuellindex = (aktuellindex + 1) % messages.length;
        return messages[aktuellindex];
    }
}



