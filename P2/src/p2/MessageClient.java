package p2;

import p1.Message;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ©author Ömer Kolsuz
 * Klassen MessageClient tar emot meddelanden i form av Message objekt. Klassen har en inre klass "Connection"
 * som ärver Thread klassen. När en message tar emot av client i Connection klassen skickas den vidare till
 * callback lyssnare via getMessage().
 */

public class MessageClient {

    private ArrayList<ICallBackInterface> callBackInterfaces;

    /**
     * Konstruktor för MessageClient som tar in två parametrar, en sträng med ip Adress och en portnummer.
     * Här skapar konstruktor en instans av inre klassen Connection och startar tråden genom att anropa start().
     * Här skapas en insans av callbackInterfaces också som håller en lista över callback lyssnare som kan läggas
     * till addCallbackListener senare.
     *
     * @param ipAdress
     * @param port
     */
    public MessageClient(String ipAdress, int port) {
        new Connection(ipAdress, port).start();
        callBackInterfaces = new ArrayList<>();
        //start();
    }

    /**
     * Metoden lägger till en ny lyssnare till arrayen av callback lyssnare. När meddelanden tas emot från
     * servern i Connection klassen, skickas meddelandet till varje callback lyssnare i arrayen..
     *
     * @param backInterface
     */
    public void addCallbackListener(ICallBackInterface backInterface) {
        callBackInterfaces.add(backInterface);
    }

    /*
    public void sendList(Message message) {
        for (ICallBackInterface callBackInterface : callBackInterfaces) {
            callBackInterface.getMessage(message);

        }
    }
*/

    /**
     * @author Ömer Kolsuz
     * Klassen connection är en inre klass som ärver från Thread och tar emot meddelanden som skickas från servern.
     * Klassen ansluter till servern via en socket och använder ObjectInputStream för att ta emot meddelanden.
     * För varje meddelande som tas emot skickas den vidare till callback lyssnare genom att anropa getMessage().
     */
    private class Connection extends Thread {
        private String ipAdress;
        private int port;

        /**
         * Konstruktor för Connection klassen som tar in två parametrar, en sträng med ip Adress och en portnummer.
         *
         * @param ipAddress
         * @param port
         */
        public Connection(String ipAddress, int port) {
            this.ipAdress = ipAddress;
            this.port = port;
            //
        }

        /**
         * Metoden run som öppnar en anslutning till en server genom att skapa en socket objekt och en
         * ObjectInputStream för att ta emot meddelande från servern. För varje meddelande tas emot skickas
         * vidare till callBackInterface genom att använda getMessage(message) metoden.
         */
        public void run() {
            try (Socket socket = new Socket(ipAdress, port)) {
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    Message message = (Message) inputStream.readObject();
                    for (ICallBackInterface callBackInterface : callBackInterfaces) { //
                        callBackInterface.getMessage(message);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
