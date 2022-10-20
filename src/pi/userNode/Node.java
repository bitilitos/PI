package pi.userNode;

import pi.gui.Window;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Node> nodes = new ArrayList<Node>();
    private Window window;

    public Node (String name) {
        this.name = name;
        window = new Window(name, this);
        window.setVisible(true);
        nodes.add(this);
    }

    public void connectToClient(String name){
        if (this instanceof ServerNode) {
            Thread clientThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Node node = new ClientNode(name, window);
                    while (true){
                        ((ClientNode)node).getClient().receiveMessage();
                    }

                }
            }); clientThread.start();
        }
    }


    public Window getWindow() {return window;}


//    @Override
//    public void run() { }
    public String getName(){return name;}

    public boolean isConnected(){
        if (this instanceof ServerNode){
            return ((ServerNode)this).getServer().isConnected();

        }
        return ((ClientNode)this).getClient().isConnected();

    }

}
