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
import java.util.List;

public class ToMain implements ActionCommand {
    private static LotService service = LotServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        CommandResult result = CommandResult.createForwardCommandResult(currentPage);
        try {
            List<Lot> lots =service.findAll();
            request.setAttribute(RequestParameter.LOT_LIST, lots);
            result = CommandResult.createForwardCommandResult(JspPath.MAIN);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
