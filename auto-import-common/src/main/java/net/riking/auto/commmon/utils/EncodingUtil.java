package net.riking.auto.commmon.utils;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {

    public static String toGb2312(String str)
            throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        String retStr = str;
        byte[] b;
        try {
            b = str.getBytes("ISO8859_1");

            for (byte b1 : b) {
                if (b1 == 63) {
                    break; //1
                } else if (b1 < 0) {
                    retStr = new String(b, "GB18030");
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); //To   change   body   of   catch   statement   use   SourceFile   |   Settings   |   SourceFile   Templates.
            throw e;
        }
        return retStr;
    }
}
