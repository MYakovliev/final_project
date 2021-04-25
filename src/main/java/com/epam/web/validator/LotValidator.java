package com.epam.web.validator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LotValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[-=:'\\.)\\s(\"\\w0-9]{4,45}");
    private static final int MAX_NAME_LENGTH = 40;
    private static final int MAX_DESCRIPTION_LENGTH = 10000;


    public static boolean isValidName(String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        boolean result = matcher.matches() && name.length() < MAX_NAME_LENGTH;
        return result;
    }

    public static boolean isValidDescription(String description) {
        return description.length() <= MAX_DESCRIPTION_LENGTH;
    }

    public static boolean isValidTime(Timestamp startTime, Timestamp finishTime) {

        boolean result = false;
        Timestamp now = new Timestamp(new Date().getTime());
        if (startTime == null) {
            result = finishTime.after(now);
        }
        if (startTime != null && finishTime != null) {
            result = startTime.after(now) && startTime.before(finishTime);
        }
        return result;
    }
}
