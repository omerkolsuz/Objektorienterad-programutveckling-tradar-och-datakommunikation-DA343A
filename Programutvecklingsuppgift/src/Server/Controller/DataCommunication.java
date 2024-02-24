package Server.Controller;

import java.io.EOFException;
import java.io.ObjectInputStream;

public class DataCommunication extends Thread{
        private ObjectInputStream inputStream;
        private Object object;
        private ClientCommunication clientComm;

        public DataCommunication(ClientCommunication clientComm) {
            this.clientComm = clientComm;
        }

        public void run(){
            try {
                inputStream = new ObjectInputStream(clientComm.getSocket().getInputStream());
                object = inputStream.readObject();
            }catch (Exception e){
                e.printStackTrace();
            }
            while (!Thread.interrupted()){
                try{
                    object = inputStream.readObject();
                    clientComm.handleInput(object);
                } catch (EOFException e){

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            clientComm.closeClient();
        }
    }
