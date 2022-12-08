package pi.gui;

import pi.connection.Message;
import pi.crypto.RSA;
import pi.userNode.ClientNode;
import pi.userNode.Node;
import pi.userNode.ServerNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Window {

    private Node node;
    private JFrame frame;
    private JTextField ipConnectToJTextField;
    private JTextField clearMessageJTextField;
    private JTextField publicKey_E_JTextField;
    private JTextField privateKey_D_JTextField;
    private JTextField publicKeyVA1JTextField;
    private JTextField publicClient_E_KeyJTextField;
    private JTextField N_KeyJTextField;
    private JTextField publicClient_N_KeyJTextField;
    private JTextField encryptedMessageJTextField;
    private JTextField messageReceivedTextField;
    private byte[] encryptedMessage;



    public Window (String name, Node node) {
        this.node = node;
        frame = new JFrame(name);
        frame.setSize(600,400);
        frame.setMinimumSize(new Dimension(600,400));
        ///frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        gbc.fill = GridBagConstraints.BOTH;


        // ROW 0
        // Set position in Grid for connectToButton
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // add connectToButton to container with gbc Layout-Position
        frame.add(createConnectToButton(),gbc);

        // ROW 1
        // Set position in Grid for clearMessageJTextField
        gbc.insets = new Insets(10,10,0,10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        frame.add(new JLabel("Mensagem a enviar:"),gbc);

        gbc.insets = new Insets(2,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        frame.add(createClearMessageJTextField(),gbc);



        // Set position in Grid for encryptButton
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(createEncryptButton(),gbc);

        // ROW 2
        // Set position in Grid for createPrivateVA1JTextField

        gbc.insets = new Insets(10,10,0,10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(new JLabel("Chave Pública:"),gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(new JLabel("Chave Privada:"),gbc);

        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(new JLabel("Chave Remota Publica:"),gbc);


        gbc.insets = new Insets(2,10,10,10);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(createPublic_E_JTextField(),gbc);

        // Set position in Grid for createPrivateVA2JTextField
        gbc.gridx = 3;
        gbc.gridy = 4;
        frame.add(createPrivate_D_JTextField(),gbc);


        // Set position in Grid for createPublicVA2JTextField
        gbc.gridx = 5;
        gbc.gridy = 4;
        frame.add(createPublicClient_E_JTextField(),gbc);



        // ROW 3
        // Set position in Grid for privateJTextField
        gbc.insets = new Insets(10,10,0,10);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        frame.add(new JLabel("Chave Comum:"),gbc);

        gbc.gridx = 5;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        frame.add(new JLabel("Chave Remota Comum:"),gbc);


        gbc.insets = new Insets(2,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        frame.add(create_N_JTextField(),gbc);


        // Set position in Grid for publicJTextField
        gbc.gridx = 5;
        gbc.gridy = 6;
        frame.add(createPublicClient_N_JTextField(),gbc);

        // ROW 4
        gbc.insets = new Insets(10,10,0,10);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 4;
        frame.add(new JLabel("Mensagem Encriptada:"),gbc);


        gbc.insets = new Insets(2,10,10,10);
        // Set position in Grid for encryptedMessageJTextField
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        frame.add(createEncryptedMessageJTextField(),gbc);

        // Set position in Grid for sendButton
        gbc.gridx = 5;
        gbc.gridy = 8;
        gbc.gridwidth = 1;

        // add connectToButton to container with gbc Layout-Position
        frame.add(createSendButton(),gbc);



        // ROW 5
        gbc.insets = new Insets(10,10,0,10);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 4;
        frame.add(new JLabel("Mensagem Recebida:"),gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 5;
        frame.add(createMessageReceivedTextField(),gbc);


    }

    public void setVisible(Boolean visible) {
        frame.setVisible(visible);
    }


    private Container createContainer () {

        Container container = new Container();
        //container.setSize(600, 400);

        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set object padding
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.BOTH;


        // ROW 0
        // Set position in Grid for connectToButton
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // add connectToButton to container with gbc Layout-Position
        container.add(createConnectToButton(),gbc);


        // Set position in Grid for ipConnectToJTextField
//        gbc.gridx = 2;
//        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        container.add(createIPJTextField(),gbc);

        // ROW 1
        // Set position in Grid for clearMessageJTextField
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 7;
        container.add(createClearMessageJTextField(),gbc);

        // Set position in Grid for encryptButton
        gbc.gridx = 8;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        container.add(createEncryptButton(),gbc);

        // ROW 2
        // Set position in Grid for createPrivateVA1JTextField
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        container.add(createPublic_E_JTextField(),gbc);

        // Set position in Grid for createPrivateVA2JTextField
        gbc.gridx = 3;
        gbc.gridy = 2;
        container.add(createPrivate_D_JTextField(),gbc);


        // Set position in Grid for createPublicVA1JTextField
        gbc.gridx = 6;
        gbc.gridy = 2;
        container.add(createPublicVA1JTextField(),gbc);

        // Set position in Grid for createPublicVA2JTextField
        gbc.gridx = 9;
        gbc.gridy = 2;
        container.add(createPublicClient_E_JTextField(),gbc);


        // ROW 3
        // Set position in Grid for privateJTextField
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        container.add(create_N_JTextField(),gbc);

        // Set position in Grid for publicJTextField
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        container.add(createPublicClient_N_JTextField(),gbc);



        // ROW 4

        // Set position in Grid for encryptedMessageJTextField
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        container.add(createEncryptedMessageJTextField(),gbc);



        // Set position in Grid for sendButton
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;

        // add connectToButton to container with gbc Layout-Position
        container.add(createSendButton(),gbc);

        // Set position in Grid for messageReceivedJTextField
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        container.add(createMessageReceivedTextField(),gbc);



        return container;

    }


    private Button createSendButton() {
        Button sendButton = new Button("Send");
        sendButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                Message message = new Message(1,encryptedMessage);
                if (node instanceof ClientNode) {
                    ((ClientNode)node).getClient().sendMessage(message, node.getName());
                }
                if (node instanceof ServerNode) {
                    ((ServerNode)node).getServer().sendMessage(message, node.getName());
                }
            }
        });
        return sendButton;
    }


    private JTextField createEncryptedMessageJTextField() {
        encryptedMessageJTextField = new JTextField("Encrypted Message");
        encryptedMessageJTextField.setText("Encrypted Message");
        return encryptedMessageJTextField;
    }


    private JTextField createPublicClient_N_JTextField() {
        publicClient_N_KeyJTextField = new JTextField("Public Key");
        publicClient_N_KeyJTextField.setText("Public Key");
        return publicClient_N_KeyJTextField;
    }

    private JTextField create_N_JTextField() {
        N_KeyJTextField = new JTextField("N Key");
        N_KeyJTextField.setText(node.getPrivateKeys()[1].toString());
        return N_KeyJTextField;
    }

    private JTextField createPublicClient_E_JTextField() {
        publicClient_E_KeyJTextField = new JTextField("Public Key VA2");
        publicClient_E_KeyJTextField.setText("Public Key VA2");
        return publicClient_E_KeyJTextField;
    }
    private JTextField createPublicVA1JTextField() {
        publicKeyVA1JTextField = new JTextField("Public Key VA1");
        publicKeyVA1JTextField.setText("Public Key VA1");
        return publicKeyVA1JTextField;
    }


    private JTextField createPrivate_D_JTextField() {
        privateKey_D_JTextField = new JTextField("D_Private");
        privateKey_D_JTextField.setText(node.getPrivateKeys()[0].toString());
        return privateKey_D_JTextField;
    }
    private JTextField createPublic_E_JTextField() {
        publicKey_E_JTextField = new JTextField("E_Public");
        publicKey_E_JTextField.setText(node.getPublicKeys()[0].toString());
        return publicKey_E_JTextField;
    }

    private Button createEncryptButton() {
        Button encryptButton = new Button("Encrypt");

        encryptButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                String message = clearMessageJTextField.getText();
                BigInteger publicE = new BigInteger(publicClient_E_KeyJTextField.getText());
                BigInteger publicN = new BigInteger(publicClient_N_KeyJTextField.getText());
                System.out.println("message:" + message + "\nmessage in bytes : " + RSA.bToS(message.getBytes()) +
                        "\nE: " + publicE + "\nN: " + publicN);
                    encryptedMessage = RSA.encryptMessage(message.getBytes(StandardCharsets.UTF_8), publicE, publicN);
                    encryptedMessageJTextField.setText(RSA.bToS(encryptedMessage));
            }
        });

        return encryptButton;
    }


    private JTextField createClearMessageJTextField() {
        clearMessageJTextField = new JTextField("Clear Message");
        clearMessageJTextField.setText("Clear Message");
        return  clearMessageJTextField;
    }

    private JTextField createIPJTextField() {
         ipConnectToJTextField = new JTextField("IP");
         ipConnectToJTextField.setText("000.000.000.000");
         return ipConnectToJTextField;
    }

    private Button createConnectToButton() {

        Button connectButton = new Button("Connect to");
        if (node instanceof ClientNode)
            connectButton.setEnabled(false);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Node node = new ClientNode("Amigo");
                    new KeyGeneratorWindow(node);
            }
        });

        return connectButton;

    }

    private JTextField createMessageReceivedTextField() {
        messageReceivedTextField = new JTextField("Private Key VA1");
        messageReceivedTextField.setText("Waiting for message");
        return messageReceivedTextField;
    }

    public void updateMessageReceivedJTextField(String msg) {
        messageReceivedTextField.setText(msg);
    }

    public String getE() {return publicKey_E_JTextField.getText();}
    public String getN() {return N_KeyJTextField.getText();}
    public String getRemoteN() {return publicClient_N_KeyJTextField.getText();}
    public String getD() {return privateKey_D_JTextField.getText();}

    public void setPublicClient_N_KeyJTextField(String key) {
        publicClient_N_KeyJTextField.setText(key);
    }

    public void setPublicClient_E_KeyJTextField(String key) {
        publicClient_E_KeyJTextField.setText(key);
    }

}
