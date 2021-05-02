package com.epam.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for user details
 */
public class UserValidator {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH=35;

    private static final Pattern MAIL_PATTERN = Pattern.compile("[^$@!]{4,20}@\\w+\\.\\w+");
    private static final Pattern LOGIN_PATTERN = Pattern.compile("[\\wА-Яа-я0-9]{4,35}");
    private static final Pattern NAME_PATTERN = Pattern.compile("[\\wА-Яа-я]{1,35}");
    private static final Pattern BID_PATTERN = Pattern.compile("[0-9]{1,10}(\\.[0-9]{0,2})?");

    public static boolean isValidName(String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        boolean result = matcher.matches();
        return result;
    }

    public static boolean isValidMail(String mail) {
        Matcher matcher = MAIL_PATTERN.matcher(mail);
        boolean result = matcher.matches();
        return result;
    }

    public static boolean isValidLogin(String login) {
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        boolean result = matcher.matches();
        return result;
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH && password.length() <= MAX_PASSWORD_LENGTH;
    }

    public static boolean isValidBid(String bid){
        Matcher matcher = BID_PATTERN.matcher(bid);
        boolean result = matcher.matches();
        return result;
    }
}
