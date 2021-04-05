package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;
import com.epam.web.service.AmountService;
import com.epam.web.service.LotService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.service.impl.AmountServiceImpl;
import com.epam.web.service.impl.LotServiceImpl;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.JspPath;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAdmin implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final LotService lotService = LotServiceImpl.getInstance();
    private static final AmountService amountService = AmountServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result;
//        if (((User) session.getAttribute(SessionAttribute.USER)).getUserRole() == UserRole.ADMIN) {
//            try {
//                Integer pageNumber = (Integer) request.getAttribute(RequestParameter.PAGE);
//                if (pageNumber == null){
//                    pageNumber = 1;
//                }
//
//                List<User> users = userService.findAll();
//                List<Lot> lots = lotService.findAll();
//                request.setAttribute(RequestParameter.USERS_LIST, users);
//                request.setAttribute(RequestParameter.LOT_LIST, lots);
//                result = CommandResult.createRedirectCommandResult(JspPath.ADMIN);
//            } catch (ServiceException e){
//                logger.error(e);
//                String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
//                result = CommandResult.createForwardCommandResult(page);
//            }
//        } else {
//            String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
            result = CommandResult.createForwardCommandResult(page);
//        }
        return result;
    }
}
