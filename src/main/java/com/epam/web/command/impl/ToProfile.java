package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.JspPath;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//todo add counting
public class ToProfile implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService service = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result = CommandResult.createForwardCommandResult(currentPage);
        try {
            long id = Long.parseLong(request.getParameter(RequestParameter.USER_ID));
            logger.debug("user id:{}",id);
            User user = service.findUserById(id);
            request.setAttribute(RequestParameter.USER, user);
            result = CommandResult.createForwardCommandResult(JspPath.PROFILE);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
