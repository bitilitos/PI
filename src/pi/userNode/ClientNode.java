package pi.userNode;

import pi.connection.Client;
import pi.gui.Window;

public class ClientNode extends Node{
    private Client client;
    public ClientNode(String name, Window window) {
        super(name);
        client = new Client(super.getWindow());
    }

    public Client getClient(){ return client;}
}
