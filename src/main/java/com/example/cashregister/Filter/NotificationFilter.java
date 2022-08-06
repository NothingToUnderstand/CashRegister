package com.example.cashregister.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for notifications
 */
@WebFilter("/*")
public class NotificationFilter implements Filter {
 private  static final String[] mes = new String[]{"mes"};
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        String errormessage = (String) session.getAttribute("errormessage");
        if (message != null&&!mes[0].equals(message)) {
            mes[0] = message;
        }else if (errormessage != null&&!mes[0].equals(errormessage)) {
            mes[0] = errormessage;
        }
            if (mes[0].equals(message)) {
                session.removeAttribute("errormessage");
            } else {
                session.removeAttribute("message");
            }

        chain.doFilter(request, response);
    }
}
