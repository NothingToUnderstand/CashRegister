package com.example.cashregister.Filter;


import com.example.cashregister.security.SecurityConfig;
import com.example.cashregister.security.UserSession;
import org.apache.log4j.Logger;

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
    private static final Logger log = Logger.getLogger(SecurityFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        log.info("Security filter works");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri= request.getRequestURI();
        String role = UserSession.getLoginedUser(request.getSession()).getRole();
        if (role.equals("admin")) {
            log.info("admin entered");
            chain.doFilter(request, response);
        } else if (SecurityConfig.getUrlPatternsForRole(role).contains(uri)) {
            log.info(role+" entered and he can visit: "+uri);
            chain.doFilter(request, response);
        } else {
            log.warn("User with role: "+role+" is not allowed to visit: "+uri);
            ((HttpServletRequest) req).getSession().setAttribute("errormessage","You are not allowed to visit this");
            response.sendRedirect("/");
        }
    }
}