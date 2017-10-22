package com.stc07.gubarkovag.servlets.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestPath = ((HttpServletRequest) request).getRequestURI();
        String action = requestPath.substring(requestPath.lastIndexOf('/') + 1, requestPath.length());

        Object user = ((HttpServletRequest)request).getSession()
                .getAttribute("user");

        if (user != null || "addUser".equals(action)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendRedirect("/courseprojectweb");
        }
    }

    @Override
    public void destroy() {

    }
}
