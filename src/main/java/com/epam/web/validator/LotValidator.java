package com.epam.web.validator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LotValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[-=')\\s(\"\\w0-9]{4,45}");
    private static final int MAX_NAME_LENGTH = 40;
    private static final int MAX_YEARS_AMOUNT_FOR_DELAYED_START = 2;


    public static boolean isValidName(String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        boolean result = matcher.matches() && name.length() < MAX_NAME_LENGTH;
        return result;
    }

    public static boolean isValidTime(Timestamp startTime, Timestamp finishTime) {

        boolean result = false;
        if (startTime != null && finishTime != null) {
            Timestamp now = new Timestamp(new Date().getTime());
            now.setYear(now.getYear() + MAX_YEARS_AMOUNT_FOR_DELAYED_START);
            result = startTime.before(now) && startTime.before(finishTime);
        }
        return result;
    }
}
