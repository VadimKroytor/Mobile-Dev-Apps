package com.example.vadim.mcalcpro;

public class Vig {

    private String key;
    public final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final String KEYSPACE = "0123456789";

    public Vig(String key) {
        this.key = key;
    }

    private String makePad(String msg) {
        String result = "";
        while (result.length() < msg.length()) {
            result = result + key;
        }
        return result;
    }

    public String encrypt(String msg) {
        String result = "";
        String pad = this.makePad(msg);
        for (int i = 0; i < msg.length(); i++) {
            String msgChar = msg.substring(i, i + 1);
            String padChar = pad.substring(i, i + 1);
            int msgPosition = ALPHA.indexOf(msgChar); // asking, given the character itself, search for it in the alphabet and convert it into a number.
            int padPosition = KEYSPACE.indexOf(padChar);
            int newPos = msgPosition + padPosition;
            String newChar = ALPHA.substring(newPos, newPos + 1);
        }
            return result;

        }

    public static void main(String[] args) {

    }
}





