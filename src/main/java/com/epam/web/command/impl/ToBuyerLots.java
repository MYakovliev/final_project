package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.service.AmountService;
import com.epam.web.service.LotService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.impl.AmountServiceImpl;
import com.epam.web.service.impl.LotServiceImpl;
import com.epam.web.util.JspPath;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToBuyerLots implements ActionCommand {
    private static LotService service = LotServiceImpl.getInstance();
    private static AmountService amountService = AmountServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();
    private static final int AMOUNT_PER_PAGE = 20;
    private static final String COMMAND_TO_PAGING = "to_buyer_lots";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result = CommandResult.createForwardCommandResult(currentPage);
        User user = (User) session.getAttribute(SessionAttribute.USER);
        try {
            String lotPageNumberString = request.getParameter(RequestParameter.LOT_PAGING);
            String error = request.getParameter("error");
            int lotPageNumber;
            if (lotPageNumberString==null){
                lotPageNumber = 1;
            } else {
                lotPageNumber = Integer.parseInt(lotPageNumberString);
            }
            int amount = amountService.findLotByBuyerIdAmount(user.getId());
            logger.debug("active lots: {}", amount);
            int pageAmount = (amount - 1 + AMOUNT_PER_PAGE) / AMOUNT_PER_PAGE;
            List<Lot> lots = service.findLotByBuyerId(user.getId(), lotPageNumber, AMOUNT_PER_PAGE);
            request.setAttribute(RequestParameter.LOT_LIST, lots);
            request.setAttribute("error", error);
            request.setAttribute(RequestParameter.LOT_PAGE_AMOUNT, pageAmount);
            request.setAttribute(RequestParameter.LOT_ACTIVE_PAGE, lotPageNumber);
            request.setAttribute(RequestParameter.COMMAND, COMMAND_TO_PAGING);
            result = CommandResult.createForwardCommandResult(JspPath.LOTS);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
