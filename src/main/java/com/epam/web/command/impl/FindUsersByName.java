package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
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

/**
 * The command that finds a list of users that contains in their name the word
 *
 * @author Nikita Yakovlev
 */
public class FindUsersByName implements ActionCommand {
    private static UserService service = UserServiceImpl.getInstance();
    private static LotService lotService = LotServiceImpl.getInstance();
    private static AmountService amountService = AmountServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();
    private static final int AMOUNT_PER_PAGE = 20;
    private static final String COMMAND_TO_LOT_PAGING = "to_admin";
    private static final String COMMAND_TO_USER_PAGING = "search_user_by_name";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result = CommandResult.createForwardCommandResult(currentPage);
        try {
            String userPageNumberString = request.getParameter(RequestParameter.USER_PAGING);
            int userPageNumber;
            if (userPageNumberString == null) {
                userPageNumber = 1;
            } else {
                userPageNumber = Integer.parseInt(userPageNumberString);
            }
            String name = request.getParameter(RequestParameter.SEARCH);
            int amount = amountService.findUserByNameAmount(name);
            logger.debug("users: {}", amount);
            int pageAmount = (amount - 1 + AMOUNT_PER_PAGE) / AMOUNT_PER_PAGE;
            List<User> users = service.findUserByName(name, userPageNumber, AMOUNT_PER_PAGE);
            request.setAttribute(RequestParameter.USER_LIST, users);
            request.setAttribute(RequestParameter.USER_PAGE_AMOUNT, pageAmount);
            request.setAttribute(RequestParameter.USER_ACTIVE_PAGE, userPageNumber);
            request.setAttribute(RequestParameter.USER_PAGING_COMMAND, COMMAND_TO_USER_PAGING);
            User user = (User) session.getAttribute(SessionAttribute.USER);
            if (user != null && user.getUserRole() == UserRole.ADMIN && currentPage.equals(JspPath.ADMIN)) {
                int lotActivePage = 1;
                request.setAttribute(RequestParameter.LOT_LIST, lotService.findAll(lotActivePage, AMOUNT_PER_PAGE));
                int lotPageAmount = (amountService.findAllUserAmount() - 1 + AMOUNT_PER_PAGE) / AMOUNT_PER_PAGE;
                request.setAttribute(RequestParameter.LOT_PAGE_AMOUNT, lotPageAmount);
                request.setAttribute(RequestParameter.LOT_ACTIVE_PAGE, lotActivePage);
                request.setAttribute(RequestParameter.LOT_PAGING_COMMAND, COMMAND_TO_LOT_PAGING);
            }
            result = CommandResult.createForwardCommandResult(JspPath.ADMIN);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
