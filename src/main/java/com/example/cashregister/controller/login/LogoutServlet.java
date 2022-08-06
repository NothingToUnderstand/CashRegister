package com.example.cashregister.controller.login;

import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        request.setAttribute("message","See you later");
        request.getSession().invalidate();
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
