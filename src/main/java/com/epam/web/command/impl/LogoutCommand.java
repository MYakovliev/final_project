package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.util.JspPath;
import com.epam.web.util.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.USER);
        return JspPath.LOGIN;
    }
}
