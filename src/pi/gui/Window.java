package pi.gui;

import pi.connection.Client;
import pi.userNode.ClientNode;
import pi.userNode.Node;
import pi.userNode.ServerNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

    private Node node;
    private JFrame frame;
    private JTextField ipConnectToJTextField;
    private JTextField clearMessageJTextField;
    private JTextField privateKeyVA1JTextField;
    private JTextField privateKeyVA2JTextField;
    private JTextField publicKeyVA1JTextField;
    private JTextField publicKeyVA2JTextField;
    private JTextField privateKeyJTextField;
    private JTextField publicKeyJTextField;
    private JTextField encryptedMessageJTextField;
    private JTextField messageReceivedTextField;



    public Window (String name, Node node) {
        this.node = node;
        frame = new JFrame(name);
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createContainer());

    }

    public void setVisible(Boolean visible) {
        frame.setVisible(visible);
    }


    private Container createContainer () {

        Container container = new Container();

        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set object padding
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        // ROW 0
        // Set position in Grid for connectToButton
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        // add connectToButton to container with gbc Layout-Position
        container.add(createConnectToButton(),gbc);


        // Set position in Grid for ipConnectToJTextField
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        container.add(createIPJTextField(),gbc);

        // ROW 1
        // Set position in Grid for clearMessageJTextField
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        container.add(createClearMessageJTextField(),gbc);

        // Set position in Grid for encryptButton
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        container.add(createEncryptButton(),gbc);

        // ROW 2
        // Set position in Grid for createPrivateVA1JTextField
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        container.add(createPrivateVA1JTextField(),gbc);

        // Set position in Grid for createPrivateVA2JTextField
        gbc.gridx = 1;
        gbc.gridy = 2;
        container.add(createPrivateVA2JTextField(),gbc);


        // Set position in Grid for createPublicVA1JTextField
        gbc.gridx = 3;
        gbc.gridy = 2;
        container.add(createPublicVA1JTextField(),gbc);

        // Set position in Grid for createPublicVA2JTextField
        gbc.gridx = 4;
        gbc.gridy = 2;
        container.add(createPublicVA2JTextField(),gbc);


        // ROW 3
        // Set position in Grid for privateJTextField
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        container.add(createPrivateJTextField(),gbc);

        // Set position in Grid for publicJTextField
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        container.add(createPublicJTextField(),gbc);



        // ROW 4

        // Set position in Grid for encryptedMessageJTextField
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        container.add(createEncryptedMessageJTextField(),gbc);



        // Set position in Grid for sendButton
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.gridwidth = 1;

        // add connectToButton to container with gbc Layout-Position
        container.add(createSendButton(),gbc);

        // Set position in Grid for messageReceivedJTextField
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        container.add(createMessageReceivedTextField(),gbc);


        return container;

    }










    private Button createSendButton() {
        Button sendButton = new Button("Send");
        sendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (node instanceof ClientNode) {
                    ((ClientNode)node).getClient().sendMessage(clearMessageJTextField.getText(), node.getName());
                }
                if (node instanceof ServerNode) {
                    ((ServerNode)node).getServer().sendMessage(clearMessageJTextField.getText(), node.getName());
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


    private JTextField createPublicJTextField() {
        publicKeyJTextField = new JTextField("Public Key");
        publicKeyJTextField.setText("Public Key");
        return publicKeyJTextField;
    }

    private JTextField createPrivateJTextField() {
        privateKeyJTextField = new JTextField("Private Key");
        privateKeyJTextField.setText("Private Key");
        return privateKeyJTextField;
    }

    private JTextField createPublicVA2JTextField() {
        publicKeyVA2JTextField = new JTextField("Public Key VA2");
        publicKeyVA2JTextField.setText("Public Key VA2");
        return publicKeyVA2JTextField;
    }
    private JTextField createPublicVA1JTextField() {
        publicKeyVA1JTextField = new JTextField("Public Key VA1");
        publicKeyVA1JTextField.setText("Public Key VA1");
        return publicKeyVA1JTextField;
    }


    private JTextField createPrivateVA2JTextField() {
        privateKeyVA2JTextField = new JTextField("Private Key VA2");
        privateKeyVA2JTextField.setText("Private Key VA2");
        return privateKeyVA2JTextField;
    }
    private JTextField createPrivateVA1JTextField() {
        privateKeyVA1JTextField = new JTextField("Private Key VA1");
        privateKeyVA1JTextField.setText("Private Key VA1");
        return privateKeyVA1JTextField;
    }

    private Button createEncryptButton() {
        Button encryptButton = new Button("Encrypt");

        encryptButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo encrypt
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
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                node.connectToClient(ipConnectToJTextField.getText());
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

}
