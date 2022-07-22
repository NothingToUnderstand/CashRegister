package com.example.cashregister.controller.security;

import com.example.cashregister.dao.UserDAO;
import com.example.cashregister.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Login servlet
 */
@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (UserSession.getLoginedUser(request.getSession()).getId() != 0) {
            response.sendRedirect("/");
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = UserDAO.validate(request.getParameter("fullname"), request.getParameter("password"));
        if (user.getId() == 0) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            boolean valid = Captcha.verify(request.getParameter("g-recaptcha-response"));
            if (!valid) {
                request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            if(request.getParameter("rememberMe")!=null){
                Cookie cUserName = new Cookie("cookname", URLEncoder.encode(user.getFullName(), "UTF-8" ));
                cUserName.setMaxAge(60 * 60 * 24) ;
                response.addCookie(cUserName);
            }else{
                Cookie cUserName = new Cookie("cookname", null);
                cUserName.setMaxAge(0);
                response.addCookie(cUserName);
            }
            UserSession.storeLoginedUser(request.getSession(), user);

            response.sendRedirect("/");
        }
    }
}
