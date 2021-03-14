package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentLanguage = (String) session.getAttribute(SessionAttribute.LOCAL_LANG);
        String newLanguage = request.getParameter(RequestParameter.LANGUAGE);
        if (!currentLanguage.equals(newLanguage)){
            session.setAttribute(SessionAttribute.LOCAL_LANG, newLanguage);
        }
        String path = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        return CommandResult.createForwardCommandResult(path);
    }
}
