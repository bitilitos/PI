package pi;


import pi.userNode.Node;
import pi.userNode.ServerNode;

public class Main {

    public static void main(String[] args) {

        Node node = new ServerNode("Eu");

//        Thread serverThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Node node = new ServerNode("Eu");
////                while (true) {
////                    ((ServerNode)node).getServer().receiveMessage();
////                }
//            }
//        });serverThread.start();
//
    }
}
