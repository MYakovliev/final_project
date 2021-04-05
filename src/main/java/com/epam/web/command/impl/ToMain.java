package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Lot;
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

public class ToMain implements ActionCommand {
    private static LotService service = LotServiceImpl.getInstance();
    private static AmountService amountService = AmountServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();
    private static final int AMOUNT_PER_PAGE = 20;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result = CommandResult.createForwardCommandResult(currentPage);
        try {
            Integer pageNumber = (Integer) request.getAttribute(RequestParameter.PAGE);
            if (pageNumber == null){
                pageNumber = 1;
            }
            int amount = amountService.findActiveLotAmount();
            int pageAmount = (amount - 1 + AMOUNT_PER_PAGE) / AMOUNT_PER_PAGE;
            List<Lot> lots = service.findActive(pageNumber, AMOUNT_PER_PAGE);
            request.setAttribute(RequestParameter.LOT_LIST, lots);
            request.setAttribute(RequestParameter.PAGE_AMOUNT, pageAmount);
            result = CommandResult.createForwardCommandResult(JspPath.LOTS);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
