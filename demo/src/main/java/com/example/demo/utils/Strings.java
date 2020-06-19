package com.example.demo.utils;

public class Strings {
    public static String format(String in, int len, char ch) {
        int ln = in.length();
        int diff = len - ln;
        StringBuffer buf = new StringBuffer();
        if (diff >= 0) {
            for (int i = 0; i < diff; i ++) {
                buf.append(ch);
            }
            buf.append(in);
        } else {
            buf.append(in.substring(0 - diff));
        }
        return buf.toString();
    }
}
