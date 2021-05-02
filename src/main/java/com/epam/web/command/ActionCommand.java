package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface that represents "Command" pattern. Used by the controller.
 */
public interface ActionCommand {

    /**
     * Method executes command
     * @param request request object from a page
     * @return CommandResult that contains a path and a way how to get to it (forward or redirect)
     */
    CommandResult execute(HttpServletRequest request);
}
