package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.JspPath;
import com.epam.web.util.SessionAttribute;
import com.epam.web.util.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static UserService userServiceImpl = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String path = JspPath.REGISTRATION;
        try {
            HttpSession session = request.getSession();
            String mail = request.getParameter(RequestParameter.MAIL);
            String password = request.getParameter(RequestParameter.PASSWORD);
            String login = request.getParameter(RequestParameter.LOGIN);
            UserRole role = UserRole.valueOf(request.getParameter(RequestParameter.ROLE).toUpperCase());
            String name = request.getParameter(RequestParameter.NAME);
            userServiceImpl.register(name, mail, login, password, role);
            path = JspPath.LOGIN;
        } catch (ServiceException e) {
            logger.error(e);
        }
        return CommandResult.createRedirectCommandResult(path);
    }
}
