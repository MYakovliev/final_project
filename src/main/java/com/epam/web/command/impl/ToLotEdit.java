package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Lot;
import com.epam.web.service.LotService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.impl.LotServiceImpl;
import com.epam.web.util.JspPath;
import com.epam.web.util.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 * The command to get to lot edit page
 *
 * @author Nikita Yakovlev
 */
public class ToLotEdit implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static LotService service = LotServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        try {
            String idString = request.getParameter(RequestParameter.LOT_ID);
            if (idString != null) {
                Lot lot = service.findLotById(Long.parseLong(idString));
                request.setAttribute(RequestParameter.LOT, lot);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return CommandResult.createForwardCommandResult(JspPath.LOT_EDIT);
    }
}
