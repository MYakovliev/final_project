package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.service.LotService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
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

public class ToLot implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static LotService service = LotServiceImpl.getInstance();
    private static UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        String error = request.getParameter(RequestParameter.ERROR);
        CommandResult result = CommandResult.createForwardCommandResult(currentPage);
        try {
            long id = Long.parseLong(request.getParameter(RequestParameter.LOT_ID));
            logger.debug("lot id:{}",id);
            Lot lot = service.findLotById(id);
            List<User> users = userService.findBuyersHistory(id);
            boolean submitted = service.isLotSubmitted(id);
            request.setAttribute(RequestParameter.SUBMITTED, submitted);
            request.setAttribute(RequestParameter.USER_LIST, users);
            request.setAttribute(RequestParameter.LOT, lot);
            request.setAttribute(RequestParameter.ERROR, error);
            result = CommandResult.createForwardCommandResult(JspPath.LOT);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
