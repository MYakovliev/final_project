package com.epam.web.controller;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.pool.ConnectionPool;
import com.epam.web.util.RequestParameter;
import com.epam.web.command.CommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();


    public void init() {
        ConnectionPool.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String command = request.getParameter(RequestParameter.COMMAND);
        logger.debug(command);
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        ActionCommand actionCommand = commandType.getCommand();
        CommandResult commandResult = actionCommand.execute(request);
        if (commandResult.isRedirect()) {
            response.sendRedirect(getServletContext().getContextPath() + commandResult.getPage());
        } else {
            getServletContext().getRequestDispatcher(commandResult.getPage()).forward(request, response);
        }
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}