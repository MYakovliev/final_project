package com.epam.web.controller.filter;

import com.epam.web.command.CommandType;
import com.epam.web.entity.User;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "banFilter", urlPatterns = {"/*"})
public class BanFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        logger.debug("It's ban filter");
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (user != null) {
            boolean isBanned = UserServiceImpl.getInstance().isBanned(user.getId());
            if (isBanned){
                logger.debug("You're banned");
                request.setAttribute(RequestParameter.COMMAND, CommandType.TO_BAN.toString());
            }
        }
        chain.doFilter(request, response);
    }
}
