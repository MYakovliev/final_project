package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.JspPath;
import com.epam.web.entity.User;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static UserService userServiceImpl = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String path = JspPath.LOGIN;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
//        logger.debug("there are two parameters {} and {}", login, password);
        try {
            User user = userServiceImpl.login(login, password);
            HttpSession session = request.getSession();
            session.setAttribute(SessionAttribute.USER, user);
            path = JspPath.MAIN;
            request.setAttribute("name", user.getName());
        } catch (ServiceException e) {
            logger.error(e);
        }
        return path;
    }
}
