package com.example.vadim.kryptonote;

import java.text.FieldPosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CipherModel {
    private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public CipherModel(String key) {
        this.key = key;
    }

    private String makePad(String note) {
        String pad = this.key;
        for (; pad.length() < note.length(); )

        {
            pad += this.key;
        }
        return pad;
    }

    public String encrypt(String note) {
        String pad = makePad(note);
        String result = "";
        for (int i = 0; i < note.length(); i++) {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i, i + 1));
            int newPosition = (position + shift) % ALPHABET.length();
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }

    public String decrypt(String note) {
        String pad = makePad(note);
        String result = "";
        for (int i = 0; i < note.length(); i++) {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i, i + 1));
            int newPosition = position - shift;
            if (newPosition > ALPHABET.length()) {
                newPosition = newPosition + ALPHABET.length();
            }
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }

    public static boolean isCdnPostalCode(String string) {
        String regex = ".*[A-Z]\\d[A-Z]\\s*\\d[A-Z]\\d.*";
        return string.matches(regex);
    }

    public static void printCdnPostalCode(String string) {
        String regex = "[A-Z&&[^DFIOQ]]\\d[A-Z]\\s*\\d[A-Z]\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string.toUpperCase());
        while (matcher.find())
         {
             System.out.println("Found a match:" );
             System.out.println(matcher.start());
             System.out.println(matcher.end());
             System.out.println(string.substring(matcher.start(), matcher.end()));
         }
    }

    //Implement the performance method which receivs a sstring made up of three int's delimited by
    //coma and quotes and returns their geometric mean (cubic root of their product).
    // The method should throw a runtime exception if s has invalid content.

    public static double performance(String string) {
        double result = 0;
        String regex = "^([+-]?\\d)+,([+-]?\\d+)'([+-]?\\d+)$"; // () denotes groups while [] denotes quantifiers
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                int nOne = Integer.parseInt(matcher.group(1));
                int nTwo = Integer.parseInt(matcher.group(2));
                int nThree = Integer.parseInt(matcher.group(3));
                double product = nOne * (double) nTwo * nThree;
                result = Math.cbrt(product);
            } else {
                throw new RuntimeException("Invalid string content");
            }

        return result;
    }
    public static int pow3(int a, int b)
    {
        int result = -1;
        for (int n = a; n <= b && result == -1; n++)
        {
            int m = n;
            while (m % 3 == 0 && m > 1)
            {
                m = m / 3;
                if (m == 1) result = n;
            }
        }
        return result;
    }

    public static void main(String[] args) { /*
    String string = "biscuits lives at w3d 0d3 and g3e 5fh";
    String secondString = "2,-3'8"; //should be 3.63
    String thirdString = "Today is Feb 25 and the time is 4:12 pm";
    printCdnPostalCode(string);
    System.out.println(isCdnPostalCode(string));
    System.out.println(performance(secondString));
    thirdString = thirdString.replaceAll("[0-9]", "*");
    System.out.println(thirdString);
    String[] token = thirdString.split("\\s+");
    for (int i = 0; i < token.length; i++) {
        System.out.println(thirdString); */
        int count = 0;
        String s = "Torontoe";
        for (int p = s.indexOf("e"); p!=-1; p = s.indexOf("e")) {
                count++;
                s = s.substring(p+1, s.length());
                System.out.println(count);


    }
        String d = "5";
        String r = "5";
        System.out.println(r.equals(d));
        System.out.println(r==d);
//pg 170
    //System.out.println(pow3(2,30));
    }
}

