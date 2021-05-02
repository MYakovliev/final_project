package com.epam.web.command;

import com.epam.web.command.impl.*;

/**
 * Enum that contain names of all commands, that are called by user, and their realisation
 *
 * @author Nikita Yakovlev
 */
public enum CommandType {
    REGISTER(new RegisterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    TO_ADMIN(new ToAdmin()),
    TO_PROFILE(new ToProfile()),
    TO_REGISTRATION(new ToRegistration()),
    TO_LOGIN(new ToLogin()),
    TO_LOTS(new ToLots()),
    TO_BAN(new ToBan()),
    TO_LOT(new ToLot()),
    TO_LOT_EDIT(new ToLotEdit()),
    TO_USER_EDIT(new ToUserEdit()),
    TO_PAYMENT(new ToPayment()),
    TO_BUYER_LOTS(new ToBuyerLots()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    ADD_LOT(new AddLotCommand()),
    MAKE_BID(new MakeBidCommand()),
    BAN_USER(new BanUser()),
    UNBAN_USER(new UnbanUser()),
    CHANGE_USER_DATA(new ChangeUserData()),
    CHANGE_USER_PASSWORD(new ChangeUserPassword()),
    SEARCH_LOT_BY_NAME(new FindLotsByName()),
    SEARCH_USER_BY_NAME(new FindUsersByName()),
    ADD_TO_BALANCE(new Pay()),
    SUBMIT_WINNER(new SubmitWinner()),
    TO_SELLER_LOTS(new ToSellerLots());

    /**
     * enum element constructor
     *
     * @param command realisation of a command
     */
    CommandType(ActionCommand command) {
        this.command = command;
    }

    ActionCommand command;

    /**
     * method to get command realisation
     *
     * @return realisation of a command
     */
    public ActionCommand getCommand() {
        return command;
    }
}
