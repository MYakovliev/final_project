package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.service.AmountService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.service.impl.AmountServiceImpl;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.JspPath;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

// FIXME: 4/20/2021 add lots and redirect to admin
public class FindUsersByName implements ActionCommand {
    private static UserService service = UserServiceImpl.getInstance();
    private static AmountService amountService = AmountServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();
    private static final int AMOUNT_PER_PAGE = 20;
    private static final String COMMAND_TO_PAGING = "find_users_by_name";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result = CommandResult.createForwardCommandResult(currentPage);
        try {
            String lotPageNumberString = request.getParameter(RequestParameter.LOT_PAGING);
            int lotPageNumber;
            if (lotPageNumberString == null) {
                lotPageNumber = 1;
            } else {
                lotPageNumber = Integer.parseInt(lotPageNumberString);
            }
            String name = request.getParameter(RequestParameter.SEARCH);
            int amount = amountService.findUserByNameAmount(name);
            logger.debug("active lots: {}", amount);
            int pageAmount = (amount - 1 + AMOUNT_PER_PAGE) / AMOUNT_PER_PAGE;
            List<User> lots = service.findUserByName(name, lotPageNumber, AMOUNT_PER_PAGE);
            request.setAttribute(RequestParameter.LOT_LIST, lots);
            request.setAttribute(RequestParameter.LOT_PAGE_AMOUNT, pageAmount);
            request.setAttribute(RequestParameter.LOT_ACTIVE_PAGE, lotPageNumber);
            request.setAttribute(RequestParameter.COMMAND, COMMAND_TO_PAGING);
            result = CommandResult.createForwardCommandResult(JspPath.ADMIN);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
