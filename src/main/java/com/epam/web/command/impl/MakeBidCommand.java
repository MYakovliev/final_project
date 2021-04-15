package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;
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


public class MakeBidCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static UserService service = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userObject = session.getAttribute(SessionAttribute.USER);
        if (userObject != null) {
            User user = (User) userObject;
            if (user.getUserRole() == UserRole.BUYER) {
                try {
                    Lot lot = (Lot) request.getAttribute(RequestParameter.LOT);
                    String bid = request.getParameter(RequestParameter.BID);
                    service.makeBid(user, bid, lot);
                } catch (ServiceException e) {
                    logger.error(e);
                }
            }
        } else {
            CommandResult.createForwardCommandResult(JspPath.LOTS);
        }
        return CommandResult.createRedirectCommandResult(JspPath.LOT);
    }
}
