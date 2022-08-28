package com.example.cashregister.controller.email;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.Service.extra.EmailServiceOtpToken;
import com.example.cashregister.dao.UserDao;
import com.example.cashregister.dao.impl.UserDaoImpl;
import com.example.cashregister.entity.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {

    @Inject
    private ServiceAbstractFactory service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/forgotPassword/forgotPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = null;
        try {
            user = service.createUserService().searchUser(request.getParameter("fullname"));
        } catch (SQLException e) {
            response.sendRedirect("/cashregister/error");
        }

        if (user.getId() != 0) {
            EmailServiceOtpToken emailServiceOtpToken = new EmailServiceOtpToken();
            emailServiceOtpToken.sendOtp(user.getEmail());
            String otp = String.valueOf(emailServiceOtpToken.getOtpvalue());
            request.getSession().setAttribute("otp", otp);
            request.getSession().setAttribute("message", "Otp was send to your email");
            response.sendRedirect("/cashregister/validateOtp?id=" + user.getId());
        } else {
            request.getSession().setAttribute("errormessage", "Your email is not valid");
            response.sendRedirect("/cashregister/login");
        }
    }

}
