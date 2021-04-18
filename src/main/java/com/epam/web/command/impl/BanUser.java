package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.service.AdminService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;
import com.epam.web.util.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BanUser implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static AdminService service = AdminServiceImpl.getInstance();
    private static final String COMMAND_TO_LOTS = "/controller?command=to_lots";
    private static final String COMMAND_TO_ADMIN = "/controller?command=to_admin&user_active_page=%d&lot_active_page=%d";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        CommandResult result = CommandResult.createRedirectCommandResult(COMMAND_TO_LOTS);
        try {
            long id = Long.parseLong(request.getParameter(RequestParameter.USER_ID));
            int userPageNumber = Integer.parseInt(request.getParameter(RequestParameter.USER_ACTIVE_PAGE));
            int lotPageNumber = Integer.parseInt(request.getParameter(RequestParameter.LOT_ACTIVE_PAGE));
            service.ban(id);
            String command = String.format(COMMAND_TO_ADMIN, userPageNumber, lotPageNumber);
            result = CommandResult.createRedirectCommandResult(command);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}
