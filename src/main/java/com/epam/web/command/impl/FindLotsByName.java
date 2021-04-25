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

public class FindLotsByName implements ActionCommand {
    private static LotService service = LotServiceImpl.getInstance();
    private static AmountService amountService = AmountServiceImpl.getInstance();
    private static UserService userService = UserServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();
    private static final int AMOUNT_PER_PAGE = 20;
    private static final String COMMAND_TO_LOT_PAGING = "search_lot_by_name";
    private static final String COMMAND_TO_USER_PAGING = "to_admin";
    private static final String COMMAND_TO_REDIRECT = "/controller?command=to_lots";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result = CommandResult.createRedirectCommandResult(COMMAND_TO_REDIRECT);
        try {
            String lotPageNumberString = request.getParameter(RequestParameter.LOT_PAGING);
            int lotPageNumber;
            if (lotPageNumberString == null) {
                lotPageNumber = 1;
            } else {
                lotPageNumber = Integer.parseInt(lotPageNumberString);
            }
            String name = request.getParameter(RequestParameter.SEARCH);
            int amount = amountService.findLotByNameAmount(name);
            logger.debug("active lots: {}", amount);
            int pageAmount = (amount - 1 + AMOUNT_PER_PAGE) / AMOUNT_PER_PAGE;
            List<Lot> lots = service.findLotByName(name, lotPageNumber, AMOUNT_PER_PAGE);
            request.setAttribute(RequestParameter.LOT_LIST, lots);
            request.setAttribute(RequestParameter.LOT_PAGE_AMOUNT, pageAmount);
            request.setAttribute(RequestParameter.LOT_ACTIVE_PAGE, lotPageNumber);
            request.setAttribute(RequestParameter.SEARCH, name);
            request.setAttribute(RequestParameter.LOT_PAGING_COMMAND, COMMAND_TO_LOT_PAGING);
            User user = (User) session.getAttribute(SessionAttribute.USER);
            if (user != null && user.getUserRole() == UserRole.ADMIN && currentPage.equals(JspPath.ADMIN)) {
                int userActivePage = 1;
                request.setAttribute(RequestParameter.USER_LIST, userService.findAll(userActivePage, AMOUNT_PER_PAGE));
                int userPageAmount = (amountService.findAllUserAmount() - 1 + AMOUNT_PER_PAGE) / AMOUNT_PER_PAGE;
                request.setAttribute(RequestParameter.USER_PAGE_AMOUNT, userPageAmount);
                request.setAttribute(RequestParameter.USER_ACTIVE_PAGE, userActivePage);
                request.setAttribute(RequestParameter.USER_PAGING_COMMAND, COMMAND_TO_USER_PAGING);
            }
            result = CommandResult.createForwardCommandResult(currentPage);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
