package pi.userNode;

import pi.connection.Server;


public class ServerNode extends Node{
    Server server;
    
     public ServerNode (String name) {
         super(name);
         server = new Server(super.getWindow());
         print();

    }

    public Server getServer() {return server;}

    private void print(){
        System.out.println("My server is " + server);
    }


    
}
