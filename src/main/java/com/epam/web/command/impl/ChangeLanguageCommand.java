package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The command that changes language in session
 *
 * @author Nikita Yakovlev
 */
public class ChangeLanguageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
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
        logger.debug(referer);
        int indexOfCommand = referer.indexOf(START_OF_THE_COMMAND);
        logger.debug(indexOfCommand);
        String subReferer = referer.substring(indexOfCommand);
        return CommandResult.createRedirectCommandResult(subReferer);
    }
}
