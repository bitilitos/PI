package pi.connection;

import pi.gui.Window;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Window window;
    private boolean connected = false;
    private long connectionTime;

    public Server(Window window) {
        this.window = window;
        startServer();

    }



    private void startServer() {
            // Create server Socket
        try{
            serverSocket = new ServerSocket(888);
            connectionTime = System.nanoTime();
            System.out.println("Waiting for clients to connect...");

                // connect it to client socket
                clientSocket = serverSocket.accept();

                System.out.println("Connection established");
            System.out.println("ConnectionTime was: " + (double)((System.nanoTime()-connectionTime)/3));
                connected = true;

        }catch(IOException e) {
            System.out.println("Unable to establish connection: " + e);
        }

    }

    public void receiveMessage(){


        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
            String message = (String) ois.readObject();

            System.out.println("Server received Message: " + message);
            window.updateMessageReceivedJTextField(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String msg, String name) {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println(name +" is sending a message...");
            oos.writeObject(msg);
        } catch (IOException e) {
            System.out.println(name + " is unable to send message!");
        }

    }

    public boolean isConnected() {return connected;}

}

