package com.exodia.common.util;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manlm1 on 10/5/2015.
 */
public class ValidateUtil {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    public static boolean isValidEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static boolean isMatched(final String s1, final String s2) {
        return s1.equals(s2);
    }

    public static void main(String[] args) {
        Pattern pattern = pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher("asdgsd@csava.com");
        System.out.println(matcher.matches());
    }
}
