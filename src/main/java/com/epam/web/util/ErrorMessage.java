package com.epam.web.util;

/**
 * Class contains constant fields as keys for resource bundle when error throws
 */
public class ErrorMessage {
    public static final String NOT_BUYER = "not_buyer";
    public static final String NOT_LOGGED_IN = "not_logged_in";
    public static final String TAKEN_LOGIN = "taken_login";
    public static final String INVALID_DATA_FORMAT = "invalid_data_format";
    public static final String INVALID_LOGIN_OR_PASSWORD = "invalid_login_or_password";
    public static final String UNKNOWN_USER = "unknown_user";
    public static final String UNKNOWN_LOT = "unknown_lot";
    public static final String AUCTION_IS_CLOSED = "auction_is_closed";
    public static final String INCORRECT_BID = "incorrect_bid";
    public static final String SMALL_BID = "small_bid";
    public static final String NOT_ENOUGH_MONEY = "not_enough_money";
    public static final String FAILED_TO_CREATE_LOT = "failed_to_create_lot";

    private ErrorMessage(){
    }
}
