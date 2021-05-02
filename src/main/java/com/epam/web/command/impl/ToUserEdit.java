package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.util.JspPath;

import javax.servlet.http.HttpServletRequest;

/**
 * The command that forwards to page where user can change his data
 *
 * @author Nikita Yakovlev
 */
public class ToUserEdit implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request){
        return CommandResult.createForwardCommandResult(JspPath.USER_EDIT);
    }

}
