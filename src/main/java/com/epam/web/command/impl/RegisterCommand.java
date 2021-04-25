package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.UserRole;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.ErrorMessage;
import com.epam.web.util.JspPath;
import com.epam.web.util.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String COMMAND_TO_REDIRECT = "/controller?command=login&login=%s&password=%s";
    private static UserService service = UserServiceImpl.getInstance();


    @Override
    public CommandResult execute(HttpServletRequest request) {
        CommandResult result = CommandResult.createForwardCommandResult(JspPath.REGISTRATION);
        String error = null;
        try {
            String mail = request.getParameter(RequestParameter.MAIL);
            String password = request.getParameter(RequestParameter.PASSWORD);
            String login = request.getParameter(RequestParameter.LOGIN);
            if (!service.isTaken(login)) {
                UserRole role = UserRole.valueOf(request.getParameter(RequestParameter.ROLE).toUpperCase());
                String name = request.getParameter(RequestParameter.NAME);
                service.register(name, mail, login, password, role);
                result = CommandResult.createRedirectCommandResult(String.format(COMMAND_TO_REDIRECT, login, password));
            } else {
                error = ErrorMessage.TAKEN_LOGIN;
                logger.debug(error);
            }
        } catch (ServiceException e) {
            error = e.getMessage();
            logger.error(e);
        }
        request.setAttribute(RequestParameter.ERROR, error);
        return result;
    }
}
