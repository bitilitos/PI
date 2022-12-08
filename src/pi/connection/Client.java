package pi.connection;

import pi.crypto.RSA;
import pi.gui.Window;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class Client {

    private Socket socket;
    private Window window;
    private boolean connected = false;
    private BigInteger clientEKey;
    private BigInteger clientNKey;

    public Client(Window window){
        this.window = window;
        connectClient();

    }

    private void connectClient() {
        try {
                System.out.println("Client trying to connect");
                socket = new Socket(InetAddress.getLocalHost(), 888);
                System.out.println("Client connected");
                connected = true;
                sendPublicKeys();

        } catch (IOException e) {
            System.out.println("Client can't connect! " + e);
        }
    }


    public void sendMessage(Message msg, String name) {
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
            Message message = (Message) ois.readObject();

            System.out.println("Client received Message: " + message);
            if (message.getType() == 0)
                getClientKey(message.getDecimal());
            else {
                BigInteger d = new BigInteger(window.getD());
                BigInteger N = new BigInteger(window.getN());
                System.out.println("D: " + d +"\n" + "N: "+ N +"\nMessage: " + message);
                byte[] decryptedMessage = RSA.decryptMessage(message.getMessage(), d, N);
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

    private void getClientKey(String[] message) {
        clientEKey = new BigInteger(message[0]);
        clientNKey = new BigInteger(message[1]);
        window.setPublicClient_E_KeyJTextField(clientEKey.toString());
        window.setPublicClient_N_KeyJTextField(clientNKey.toString());

    }

    private void sendPublicKeys() {
        //sendMessage("|" + window.getE() + ":" + window.getN(), "Server");
        String[]decimals = new String[2];
        decimals[0] = window.getE();
        decimals[1] = window.getN();
        new Message(0,decimals);
        sendMessage(new Message(0,decimals), "client");
    }


    public boolean isConnected() {return connected;}


}
