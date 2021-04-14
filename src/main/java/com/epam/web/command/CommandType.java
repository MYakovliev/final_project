package com.epam.web.command;

import com.epam.web.command.impl.*;

//TODO add commands
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
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    ADD_LOT(new AddLotCommand()),
    MAKE_BID(new MakeBidCommand());

    CommandType(ActionCommand command) {
        this.command = command;
    }

    ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
