package com.epam.web.command;

import com.epam.web.command.impl.*;

//TODO add commands and change to commands
public enum CommandType {
    REGISTER(new RegisterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    TO_ADMIN(new ToAdmin()),
    TO_REGISTRATION(new ToRegistration()),
    TO_LOGIN(new ToLogin()),
    TO_MAIN(new ToMain()),
    TO_BAN(new ToBan()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    MAKE_BID(new MakeBidCommand());

    CommandType(ActionCommand command) {
        this.command = command;
    }

    ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
