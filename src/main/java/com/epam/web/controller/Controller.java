package com.epam.web.controller;

import com.epam.web.command.ActionCommand;
import com.epam.web.pool.ConnectionPool;
import com.epam.web.util.RequestParameter;
import com.epam.web.command.CommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger();


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
        String path = actionCommand.execute(request);
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}