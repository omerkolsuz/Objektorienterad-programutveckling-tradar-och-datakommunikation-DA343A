package p2;

import p1.Message;
import p1.Viewer;

public class P2Viewer extends Viewer implements ICallBackInterface {
    private MessageClient messageClient;

    public P2Viewer(MessageClient messageClient, int width, int height) {
        super(width, height);
        this.messageClient = messageClient;
        messageClient.addCallbackListener(this);

    }

    @Override
    public void getMessage(Message msg) {
        setMessage(msg);
    }
}
