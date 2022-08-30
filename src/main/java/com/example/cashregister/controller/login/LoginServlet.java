package com.example.cashregister.controller.login;



import com.example.cashregister.entity.User;
import com.example.cashregister.security.Captcha;
import com.example.cashregister.security.UserSession;
import com.example.cashregister.security.ValidateUser;

import org.apache.log4j.Logger;


import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import static com.example.cashregister.Service.extra.Notifications.setErrormessage;
import static com.example.cashregister.Service.extra.Notifications.setMessage;


/**
 * Login servlet
 */
@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);
    @Inject
    private ValidateUser validateUser;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (UserSession.getLoginedUser(request.getSession()).getId() != 0) {
            log.info("user has already logined");
            setErrormessage("You are already logged in");
            response.sendRedirect("/cashregister/");
        } else {
            log.warn("user is not logined");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        try {
            user = this.validateUser.validateUser(request.getParameter("fullname"),
                    request.getParameter("password"));
        } catch (SQLException e) {
            log.error("Error during user validation", e);
            response.sendRedirect("/cashregister/error");
        }
        if (user == null) {
            log.warn("user is null");
            setErrormessage("There is no such user");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (!Captcha.verify(request.getParameter("g-recaptcha-response"))) {
            request.getSession().setAttribute("errormessage", "Captcha is not valid");
            log.warn("captcha is not valid");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (request.getParameter("rememberMe") != null) {
            log.info("rememberMe is true");
            Cookie cUserName = new Cookie("cookname", URLEncoder.encode(user.getFullName(), "UTF-8"));
            cUserName.setMaxAge(60 * 60 * 24);
            response.addCookie(cUserName);
        } else {
            log.warn("rememberMe is false");
            Cookie cUserName = new Cookie("cookname", null);
            cUserName.setMaxAge(0);
            response.addCookie(cUserName);
        }


        UserSession.storeLoginedUser(request.getSession(), user);
        setMessage("Welcome: " + user.getFullName());
        response.sendRedirect("/cashregister/acc");
    }
}


