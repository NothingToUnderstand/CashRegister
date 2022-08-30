package com.example.cashregister.controller.login;

import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.cashregister.Service.extra.Notifications.setMessage;

/**
 * Logout servlet
 */
@WebServlet(name = "logout", value = "/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LogoutServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("LogOut");
        setMessage("See you later");
        request.setAttribute("lang","en");
        request.getSession().invalidate();
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
