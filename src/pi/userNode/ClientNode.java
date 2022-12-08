package pi.userNode;

import pi.connection.Client;
import pi.gui.Window;

public class ClientNode extends Node implements Runnable{
    private Client client;
    public ClientNode(String name) {
        super(name);

    }

    public Client getClient(){ return client;}

    @Override
    public void run() {
        Window window = new Window("Amigo", this );
        window.setVisible(true);
        super.setWindow(window);
        client = new Client(window);

        while (true){
            getClient().receiveMessage();
        }
    }
}
