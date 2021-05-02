package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.util.JspPath;

import javax.servlet.http.HttpServletRequest;

/**
 * The command to redirect to ban page
 *
 * @author Nikita Yakovlev
 */
public class ToBan implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.createRedirectCommandResult(JspPath.BAN);
    }
}
