package com.example.cashregister.controller.user;


import com.example.cashregister.Service.UserService;
import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.security.UserSession;
import com.example.cashregister.dao.UserDao;
import com.example.cashregister.dao.impl.UserDaoImpl;
import org.apache.log4j.Logger;

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
import static com.example.cashregister.security.PasswordEncryptionService.generateSalt;
import static com.example.cashregister.security.PasswordEncryptionService.getEncryptedPassword;
import static com.example.cashregister.security.UserSession.getLoginedUser;

/**
 * Create user servlet
 */
@WebServlet(name = "createUser", value = "/create/user")
public class CreateUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateUser.class);
    @Inject
    private ServiceAbstractFactory service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        if (getLoginedUser(req.getSession()).getId() != 0) {
            log.info("user has already logined");
            setErrormessage("You are already logged in");
            resp.sendRedirect("/cashregister/");
        } else {
            getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            if (service.createUserService().searchUser(req.getParameter("firstname") + " " + req.getParameter("lastname")) != null) {
                setErrormessage("User with such fullname has already exist");
            } else {
                int id = service.createUserService().createUser(
                        req.getParameter("firstname"),
                        req.getParameter("lastname"),
                        req.getParameter("password"),
                        req.getParameter("roleid"),
                        req.getParameter("email"));
                setMessage("User was created with id: " + id);
            }
            resp.sendRedirect("/cashregister/login");
        } catch (SQLException e) {
            log.error("error during user creation", e);
            resp.sendRedirect("/cashregister/error");
        } catch (NumberFormatException e) {
            log.warn("not valid params", e);
            setErrormessage("Params are not valid");
            resp.sendRedirect("/cashregister/create/user");
        }
    }
}
