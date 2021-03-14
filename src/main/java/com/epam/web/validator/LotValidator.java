package com.epam.web.validator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LotValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[-=')(\"\\w0-9]{4,45}");


    public static boolean isValidName(String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        boolean result = matcher.matches() && name.length() < 40;
        return result;
    }

    public static boolean isValidTime(Timestamp startTime, Timestamp finishTime) {
        boolean result = startTime.before(finishTime);
        Timestamp now = new Timestamp(new Date().getTime());
        result = (startTime.after(now) || startTime.equals(now)) && result;
        return result;
    }
}
