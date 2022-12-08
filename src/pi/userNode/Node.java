package pi.userNode;

import pi.gui.Window;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Node> nodes = new ArrayList<Node>();
    private Window window;
    private BigInteger P, Q, N, PHI, e, d;

// Receber P e Q do key Generator
    // Enviar para o RSA
    public Node (String name) {
        this.name = name;
        // window = new Window(name, this);
        nodes.add(this);
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public BigInteger[] getPublicKeys() {
        BigInteger[] publicKeys = new BigInteger[2];
        publicKeys[0] = e;
        publicKeys[1] = N;
        return publicKeys;
    }

    public BigInteger[] getPrivateKeys() {
        BigInteger[] publicKeys = new BigInteger[2];
        publicKeys[0] = d;
        System.out.println("d:"+ d);
        publicKeys[1] = N;
        return publicKeys;
    }

        //        node.setPublicKey(e,N);
    public void setPublicKey(BigInteger P,BigInteger e,BigInteger N) {
        this.P = P;
        this.e = e;
        this.N = N;
    }


    public BigInteger getP () {return P;}
    public BigInteger getQ () {return Q;}
    public BigInteger getN () {return N;}


        //node.setPrivateKey(d,N);
        public void setPrivateKey(BigInteger Q, BigInteger d,BigInteger N) {
            this.Q = Q;
            this.d = d;
            this.N = N;
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
