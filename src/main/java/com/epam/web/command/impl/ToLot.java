package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Lot;
import com.epam.web.service.LotService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.impl.LotServiceImpl;
import com.epam.web.util.JspPath;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToLot implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final LotService service = LotServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result = CommandResult.createForwardCommandResult(currentPage);
        try {
            long id = Long.parseLong(request.getParameter(RequestParameter.LOT_ID));
            logger.debug("lot id:{}",id);
            Lot lot = service.findLotById(id);
            request.setAttribute(RequestParameter.LOT, lot);
            result = CommandResult.createForwardCommandResult(JspPath.LOT);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
