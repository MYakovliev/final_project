package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.util.JspPath;

import javax.servlet.http.HttpServletRequest;

/**
 * The command that forwards to registration page
 *
 * @author Nikita Yakovlev
 */
public class ToRegistration implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.createForwardCommandResult(JspPath.REGISTRATION);
    }
}
