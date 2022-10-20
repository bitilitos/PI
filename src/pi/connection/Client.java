package pi.connection;

import pi.gui.Window;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client {

    private Socket socket;
    private Window window;
    private boolean connected = false;

    public Client(Window window){
        connectClient();
        this.window = window;

    }

    private void connectClient() {
        try {
                System.out.println("Client trying to connect");
                socket = new Socket(InetAddress.getLocalHost(), 888);
                System.out.println("Client connected");
                connected = true;

        } catch (IOException e) {
            System.out.println("Client can't connect! " + e);
        }
    }


    public void sendMessage(String msg, String name) {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(name +" is sending a message...");
            oos.writeObject(msg);
        } catch (IOException e) {
            System.out.println(name + " is unable to send message!");
        }
    }


    public void receiveMessage(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();

            System.out.println("Client received Message: " + message);
            window.updateMessageReceivedJTextField(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean isConnected() {return connected;}


}
