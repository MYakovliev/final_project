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
import java.math.BigDecimal;

/**
 * The command to add money to user's balance
 *
 * @author Nikita Yakovlev
 */
public class Pay implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static UserService service = UserServiceImpl.getInstance();
    private static final String COMMAND_TO_REDIRECT = "/controller?command=to_lots";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        CommandResult result = CommandResult.createRedirectCommandResult(COMMAND_TO_REDIRECT);
        try {
            String payment = request.getParameter(RequestParameter.BID);
            service.addBalance(user.getId(), payment);
            user.setBalance(user.getBalance().add(new BigDecimal(payment)));
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestParameter.ERROR, e.getMessage());
            result = CommandResult.createForwardCommandResult(JspPath.PAYMENT);
        }
        return result;
    }
}
