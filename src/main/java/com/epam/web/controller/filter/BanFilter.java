package com.epam.web.controller.filter;

import com.epam.web.command.CommandType;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.runtime.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter that checks if current user is banned
 *
 * @author Nikita Yakovlev
 */
@WebFilter(filterName = "banFilter", urlPatterns = {"/*"})
public class BanFilter implements Filter {
    private static UserService service = UserServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (user != null) {
            boolean isBanned = false;
            try {
                isBanned = service.isBanned(user.getId());
            } catch (ServiceException e) {
                logger.error(e);
            }
            if (isBanned) {
                String command = request.getParameter(RequestParameter.COMMAND);
                if (command != null && CommandType.valueOf(command.toUpperCase()) != CommandType.LOGOUT) {
                    request.setAttribute(RequestParameter.COMMAND, CommandType.TO_BAN);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
