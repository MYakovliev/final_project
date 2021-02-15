package com.epam.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidator {
    private static final int MIN_PASSWORD_LENGTH = 8;

    private static Pattern mailPattern = Pattern.compile("[^$@!]{4,20}@\\w+\\.\\w+");
    public boolean isValidMail(String mail) {
        Matcher matcher = mailPattern.matcher(mail);
        boolean result = matcher.matches() && mail.length() < 40;
        return result;
    }

    public boolean isValidPassword(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }
}
