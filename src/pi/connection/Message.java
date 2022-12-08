package pi.connection;

import java.io.Serializable;

public class Message implements Serializable {




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

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public void setDecimal(String[] decimal) {
        this.decimal = decimal;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte[] getMessage() {
        return message;
    }

    public String[] getDecimal() {
        return decimal;
    }

    public int getType() {
        return type;
    }





}
