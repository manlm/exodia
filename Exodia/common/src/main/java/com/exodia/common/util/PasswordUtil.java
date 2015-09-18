package com.exodia.common.util;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Created by manlm1 on 9/17/2015.
 */
public class PasswordUtil {

    private static final Logger LOG = Logger.getLogger(MemcachedClient.class);

    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM = "0123456789";
    private static final String SPL_CHARS = "@$!%*?&#";

    /**
     * Generate password
     *
     * @return
     */
    public static char[] generatePswd() {
        LOG.info("[generatePswd] Start");

        Random rand = new Random();

        int minLen = 8;
        int maxLen = 8;

        int max = 5;
        int min = 1;

        int noOfCAPSAlpha = rand.nextInt((max - min) + 1) + min;

        max = 6 - noOfCAPSAlpha;
        int noOfDigits = rand.nextInt((max - min) + 1) + min;

        max = 7 - noOfCAPSAlpha - noOfDigits;
        int noOfSplChars = rand.nextInt((max - min) + 1) + min;

        if (minLen > maxLen)
            throw new IllegalArgumentException("Min. Length > Max. Length!");
        if ((noOfCAPSAlpha + noOfDigits + noOfSplChars) > minLen)
            throw new IllegalArgumentException
                    ("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
        Random rnd = new Random();
        int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
        char[] pswd = new char[len];
        int index = 0;
        for (int i = 0; i < noOfCAPSAlpha; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
        }
        for (int i = 0; i < noOfDigits; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
        }
        for (int i = 0; i < noOfSplChars; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
        }
        for (int i = 0; i < len; i++) {
            if (pswd[i] == 0) {
                pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
            }
        }

        LOG.info("[generatePswd] End");
        return pswd;
    }

    /**
     * Generate random int
     *
     * @param rnd
     * @param len
     * @param pswd
     * @return
     */
    private static int getNextIndex(Random rnd, int len, char[] pswd) {
        int index = rnd.nextInt(len);
        while (pswd[index = rnd.nextInt(len)] != 0) ;
        return index;
    }

    public static void main(String[] args) {

        Random rand = new Random();

        int max = 5;
        int min = 1;

        int noOfCAPSAlpha = rand.nextInt((max - min) + 1) + min;
        System.out.println(noOfCAPSAlpha);

        max = 6 - noOfCAPSAlpha;
        int noOfDigits = rand.nextInt((max - min) + 1) + min;
        System.out.println(noOfDigits);

        max = 7 - noOfCAPSAlpha - noOfDigits;
        int noOfSplChars = rand.nextInt((max - min) + 1) + min;
        System.out.println(noOfCAPSAlpha);

        int minLen = 8;
        int maxLen = 8;

        for (int i = 0; i < 10; i++) {
            char[] pswd = generatePswd();
            System.out.println("Len = " + pswd.length + ", " + new String(pswd));
        }
    }
}
