package com.example.demo.utils;

public class UBytes {
    public static String toHexString(byte[] in) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < in.length; i ++) {
            byte by = in[i];
            String str = Strings.format(Integer.toHexString(by), 2, '0');
            buf.append(str);
            buf.append(" ");
            if ((i + 1) % 16 == 0) {
                buf.append("\n");
            }
        }
        return buf.toString();
    }
}
