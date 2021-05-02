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

/**
 * The command that changes user password
 *
 * @author Nikita Yakovlev
 */
public class ChangeUserPassword implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static UserService service = UserServiceImpl.getInstance();
    private static final String COMMAND_TO_REDIRECT = "/controller?command=to_profile&user_id=%d";
    private static final String COMMAND_IF_ERROR = "/controller?command=to_user_edit&error=%s";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        CommandResult result = CommandResult.createRedirectCommandResult(String.format(COMMAND_TO_REDIRECT, user.getId()));
        try {
            String oldPassword = request.getParameter(RequestParameter.OLD_PASSWORD);
            String newPassword = request.getParameter(RequestParameter.PASSWORD);
            service.changeUserPassword(user.getId(), oldPassword, newPassword);
        } catch (ServiceException e) {
            result = CommandResult.createRedirectCommandResult(String.format(COMMAND_IF_ERROR, e.getMessage()));
            logger.error(e);
        }
        return result;
    }
}
