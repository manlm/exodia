package com.exodia.common.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by manlm1 on 9/12/2015.
 */
public class MD5Util {

    private static final Logger LOG = Logger.getLogger(MD5Util.class);

    /**
     * Convert string to MD5
     *
     * @param s
     * @return
     */
    public static String stringToMD5(String s) {
        LOG.info(new StringBuilder("[stringToMD5] Start: s = ").append(s));
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");

            md.update(s.getBytes());

            byte byteData[] = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            LOG.info("[stringToMD5] End");
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.error(new StringBuilder("[stringToMD5] NoSuchAlgorithmException").append(e.getMessage()));
        }
        LOG.info("[stringToMD5] End");
        return null;
    }

    public static void main(String[] args) throws Exception {
        String password = "Superbuu1803#";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("Digest(in hex format):: " + hexString.toString());
        System.out.println(MD5Util.stringToMD5("$SlpC6CE"));
    }
}
