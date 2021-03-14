package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.util.JspPath;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class MakeBidCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        int id = Integer.parseInt(request.getParameter(RequestParameter.LOT_ID));
        String bid = request.getParameter(RequestParameter.BID);

        return CommandResult.createRedirectCommandResult(JspPath.LOT);
    }
}
