package com.eclubprague.cardashboard.core.utils;

/**
 * Created by Michael on 16.09.2015.
 */
public class StringValidator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public static boolean isValid(String input, String regex) {
        return input.matches(regex);
    }

    public static boolean isEmail(String input) {
        return input.matches(EMAIL_PATTERN);
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isDecimal(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isEmpty(String input) {
        return input.isEmpty();
    }

    public static boolean isLongEnough(String input, int minLength) {
        return input.length() >= minLength;
    }

}
