package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    private static final String REFERER = "referer";
    private static final String START_OF_THE_COMMAND = "/controller";


    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentLanguage = (String) session.getAttribute(SessionAttribute.LOCAL_LANG);
        String newLanguage = request.getParameter(RequestParameter.LANGUAGE);
        if (!currentLanguage.equals(newLanguage)){
            session.setAttribute(SessionAttribute.LOCAL_LANG, newLanguage);
        }
        String referer = request.getHeader(REFERER);
        String subReferer = referer.substring(referer.indexOf(START_OF_THE_COMMAND));
        return CommandResult.createRedirectCommandResult(subReferer);
    }
}
