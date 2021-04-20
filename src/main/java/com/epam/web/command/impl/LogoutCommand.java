package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    private static final String COMMAND_TO_REDIRECT = "/controller?command=to_lots";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return CommandResult.createRedirectCommandResult(COMMAND_TO_REDIRECT);
    }
}
