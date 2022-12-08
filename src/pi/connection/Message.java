package pi.connection;

public class Message {

    private byte[] message;
    private String[] decimal ;
    private int type;

    public Message(int type, String[] decimal) {
        this.type = type;
        this.decimal = decimal;
    }

    public Message(int type, byte[] message) {
        this.type = type;
        this.message = message;
    }

}
