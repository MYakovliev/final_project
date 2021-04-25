package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;
import com.epam.web.service.AdminService;
import com.epam.web.service.LotService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;
import com.epam.web.service.impl.LotServiceImpl;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SubmitWinner implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static AdminService service = AdminServiceImpl.getInstance();
    private static LotService lotService = LotServiceImpl.getInstance();
    private static final String COMMAND_TO_REDIRECT = "/controller?command=to_admin";


    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        long lotId = Long.parseLong(request.getParameter(RequestParameter.LOT_ID));
        CommandResult result = CommandResult.createRedirectCommandResult(COMMAND_TO_REDIRECT);
        try {
            if (user != null && user.getUserRole() == UserRole.ADMIN) {
                Lot lot = lotService.findLotById(lotId);
                service.submitWinner(lot.getBuyerId(), lotId);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
