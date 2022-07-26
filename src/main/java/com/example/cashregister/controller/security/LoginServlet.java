package com.example.cashregister.controller.security;

import com.example.cashregister.dao.UserDAO;
import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;

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
    private static final Logger log = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("doGet login");
        if (UserSession.getLoginedUser(request.getSession()).getId() != 0) {
            log.info("user has already logined");
request.getSession().setAttribute("errormessage","You are already logged in");
            response.sendRedirect("/");
        } else {
            log.warn("user is not logined");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost");
        User user = UserDAO.validate(request.getParameter("fullname"), request.getParameter("password"));
        if (user.getId() == 0) {
            log.warn("doPost user id is 0");
            request.getSession().setAttribute("errormessage","User fullname or password are invalid");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            log.info("doPost user logined successfully");
            boolean valid = Captcha.verify(request.getParameter("g-recaptcha-response"));
            if (!valid) {
                request.getSession().setAttribute("errormessage","Captcha is not valid");
                log.warn("captcha is not valid");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            if(request.getParameter("rememberMe")!=null){
                log.info("rememberMe is true");
                Cookie cUserName = new Cookie("cookname", URLEncoder.encode(user.getFullName(), "UTF-8" ));
                cUserName.setMaxAge(60 * 60 * 24) ;
                response.addCookie(cUserName);
            }else{
                log.warn("rememberMe is false");
                Cookie cUserName = new Cookie("cookname", null);
                cUserName.setMaxAge(0);
                response.addCookie(cUserName);
            }
            UserSession.storeLoginedUser(request.getSession(), user);
            request.getSession().setAttribute("message","Welcome: "+user.getFullName());
            response.sendRedirect("/acc/"+user.getRole());
        }
    }

}
