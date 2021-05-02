package com.epam.web.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter to set encodings to request and response objects
 */
public class CharacterSetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
