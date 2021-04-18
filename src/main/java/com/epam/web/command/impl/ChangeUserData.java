package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeUserData implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static UserService service = UserServiceImpl.getInstance();
    private static final String COMMAND_TO_REDIRECT = "/controller?command=to_profile&user_id=%d";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        CommandResult result = CommandResult.createRedirectCommandResult(String.format(COMMAND_TO_REDIRECT, user.getId()));
        try {
            String avatar = ((List<String>)request.getAttribute(RequestParameter.IMAGE_PATH)).get(0);
            String name = request.getParameter(RequestParameter.NAME);
            String mail = request.getParameter(RequestParameter.MAIL);
            service.changeUserData(user.getId(), avatar, name, mail);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}