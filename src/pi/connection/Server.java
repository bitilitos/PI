package pi.connection;

import pi.crypto.RSA;
import pi.gui.Window;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Window window;
    private boolean connected = false;
    private long connectionTime;
    private BigInteger clientEKey;
    private BigInteger clientNKey;


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
            if (message.charAt(0) == '|')
                getClientKey(message);
            else {
                BigInteger d = new BigInteger(window.getD());
                BigInteger N = new BigInteger(window.getN());
                System.out.println("D: " + d +"\n" + "N: "+ N +"\nMessage: " + message);
                byte[] decryptedMessage = RSA.decryptMessage(message.getBytes(StandardCharsets.UTF_8), d, N);
                System.out.println(decryptedMessage);
                System.out.println(new String(decryptedMessage));
                window.updateMessageReceivedJTextField(new String(decryptedMessage));

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void getClientKey(String message) {

        String keysString[] = message.substring(1).split(":");
        clientEKey = new BigInteger(keysString[0]);
        clientNKey = new BigInteger(keysString[1]);
        window.setPublicClient_E_KeyJTextField(clientEKey.toString());
        window.setPublicClient_N_KeyJTextField(clientNKey.toString());
        sendPublicKeys();
    }

    private void sendPublicKeys() {
        sendMessage("|" + window.getE() + ":" + window.getN(), "Server");
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

