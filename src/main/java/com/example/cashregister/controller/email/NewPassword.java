package com.example.cashregister.controller.email;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.dao.UserDao;
import com.example.cashregister.dao.impl.UserDaoImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.cashregister.Service.extra.Notifications.setErrormessage;
import static com.example.cashregister.Service.extra.Notifications.setMessage;
import static com.example.cashregister.security.PasswordEncryptionService.getEncryptedPassword;

/**
 * Servlet implementation class NewPassword
 */
@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {
    @Inject
    private ServiceAbstractFactory service;

    private String id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getHeader("referer") == null) {
            setErrormessage("You should validate token before visit this");
            resp.sendRedirect("/cashregister/");
            return;
        }
        id = req.getParameter("id");
        getServletContext().getRequestDispatcher("/forgotPassword/newPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            if (service.createUserService().updatePasswordByEmail(id, req.getParameter("password"))) {
                setMessage("Password was changed successfully");
            } else {
                setErrormessage("Password was not changed");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/cashregister/error");
        }
        resp.sendRedirect("/cashregister/login");
    }
}
