/**
 * 
 */
package org.mspring.platform.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class CodecUtils {
    public static String encrypt(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] output = messageDigest.digest(str.getBytes());
            StringBuffer sb = new StringBuffer(2 * output.length);
            for (int i = 0; i < output.length; i++) {
                int k = output[i] & 0xFF;
                if (k < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(k));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(encrypt("gaoyoubo"));
    }
}
