package pi.userNode;

import pi.connection.Server;
import pi.gui.KeyGeneratorWindow;
import pi.gui.Window;

import java.awt.*;


public class ServerNode extends Node implements Runnable{
    Server server;
    
     public ServerNode (String name) {
         super(name);

         KeyGeneratorWindow keyGeneratorWindow = new KeyGeneratorWindow(this);
         //server = new Server(super.getWindow());
         print();

    }

    public Server getServer() {return server;}

    private void print(){
        System.out.println("My server is " + server);
    }


    @Override
    public void run() {
        Window window = new Window("Eu", this );
         window.setVisible(true);
         super.setWindow(window);
        server = new Server(window);

                while (true) {
                   getServer().receiveMessage();
                }
    }
}
