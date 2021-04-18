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
    private static final int AMOUNT_PER_PAGE = 5;
    private static final String COMMAND_TO_PAGING = "to_admin";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result;
        if (((User) session.getAttribute(SessionAttribute.USER)).getUserRole() == UserRole.ADMIN) {
            try {
                String lotPageNumberString = request.getParameter(RequestParameter.LOT_PAGING);
                int lotPageNumber;
                logger.debug("lotPageNumber:{}", lotPageNumberString);
                if (lotPageNumberString==null){
                    lotPageNumber = 1;
                } else {
                    lotPageNumber = Integer.parseInt(lotPageNumberString);
                }
                String userPageNumberString = request.getParameter(RequestParameter.USER_PAGING);
                int userPageNumber;
                logger.debug("userPageNumber:{}", userPageNumberString);
                if (userPageNumberString==null){
                    userPageNumber = 1;
                } else {
                    userPageNumber = Integer.parseInt(userPageNumberString);
                }

                int userAmount = amountService.findAllUserAmount();
                int lotAmount = amountService.findAllLotAmount();
                int userPageAmount = (userAmount - 1 + AMOUNT_PER_PAGE) / AMOUNT_PER_PAGE;
                int lotPageAmount = (lotAmount - 1 + AMOUNT_PER_PAGE) / AMOUNT_PER_PAGE;
                List<User> users = userService.findAll(userPageNumber, AMOUNT_PER_PAGE);
                List<Lot> lots = lotService.findAll(lotPageNumber, AMOUNT_PER_PAGE);
                request.setAttribute(RequestParameter.USERS_LIST, users);
                request.setAttribute(RequestParameter.LOT_LIST, lots);
                request.setAttribute(RequestParameter.LOT_PAGE_AMOUNT, lotPageAmount);
                request.setAttribute(RequestParameter.USER_PAGE_AMOUNT, userPageAmount);
                request.setAttribute(RequestParameter.LOT_ACTIVE_PAGE, lotPageNumber);
                request.setAttribute(RequestParameter.USER_ACTIVE_PAGE, userPageNumber);
                request.setAttribute(RequestParameter.COMMAND, COMMAND_TO_PAGING);
                result = CommandResult.createForwardCommandResult(JspPath.ADMIN);
            } catch (ServiceException e){
                logger.error(e);
                result = CommandResult.createForwardCommandResult(page);
            }
        } else {
            result = CommandResult.createForwardCommandResult(page);
        }
        return result;
    }
}
