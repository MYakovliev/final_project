package com.epam.web.command;

import com.epam.web.command.impl.*;

//TODO add commands
public enum CommandType {
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    TO_REGISTRATION {
        {
            this.command = new ToRegistration();
        }
    },
    TO_LOGIN{
        {
            this.command = new ToLogin();
        }
    },
    TO_MAIN{
        {
            this.command = new ToMain();
        }
    },
    TO_BAN{
        {
            this.command = new ToBan();
        }
    },
    CHANGE_LANGUAGE{
        {
            this.command = new ChangeLanguageCommand();
        }
    },
    MAKE_BID{
        {
            this.command = new MakeBidCommand();
        }
    };


    ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
