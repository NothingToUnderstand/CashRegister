package com.example.cashregister.controller.security;


import com.example.cashregister.entity.User;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Security filter for users
 */
@WebFilter("/*")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String role = UserSession.getLoginedUser(request.getSession()).getRole();
        if (role.equals("admin")) {
            chain.doFilter(request, response);
        } else if (SecurityConfig.getUrlPatternsForRole(role).contains(request.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}