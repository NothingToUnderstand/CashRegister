package com.example.cashregister.controller.user;


import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.dao.UserDao;
import com.example.cashregister.dao.impl.UserDaoImpl;
import com.example.cashregister.entity.User;
import com.example.cashregister.security.UserSession;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.cashregister.security.PasswordEncryptionService.getEncryptedPassword;
import static com.example.cashregister.security.UserSession.getLoginedUser;
import static com.example.cashregister.security.UserSession.storeLoginedUser;

/**
 * Update user servlet
 */
@WebServlet(name = "updateUser", value = "/update/user")
public class UpdateUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(UpdateUser.class);
    @Inject
    private ServiceAbstractFactory service;
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = getLoginedUser(req.getSession());
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            req.getSession().setAttribute("errormessage", "id is not valid");
            resp.sendRedirect("/cashregister/update/user");
            return;
        }
        if (id != user.getId() && !user.getRole().equals("admin")) {
            resp.sendRedirect("/cashregister/");
            req.getSession().setAttribute("errormessage", "you can`t change another user");
            return;
        }
        try {
            req.setAttribute("user", service.createUserService().get(req.getParameter("id")));
        } catch (SQLException e) {
            resp.sendRedirect("/cashregister/error");
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            req.getSession().setAttribute("errormessage", "id is not valid");
            resp.sendRedirect("/cashregister/update/user");
            return;
        }
        getServletContext().getRequestDispatcher("/forAdmin/updateuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = getLoginedUser(req.getSession());
        User updateduser = null;
        try {
            updateduser= service.createUserService().updateUser(
                    req.getParameter("id"),
                    req.getParameter("firstname"),
                    req.getParameter("lastname"),
                    req.getParameter("password"),
                    req.getParameter("roleid"),
                    req.getParameter("email"));
                log.info("user was updated");
                req.getSession().setAttribute("message", "user was updated");
        } catch (SQLException e) {
            log.error("error during updating user");
            resp.sendRedirect("/error");
        } catch (NumberFormatException e) {
            log.error("params not valid");
            req.getSession().setAttribute("errormessage", "params are not valid");
            resp.sendRedirect("/cashregister/update/user");
        }
        if (updateduser==null){
            req.getSession().setAttribute("errormessage", "There is no such user");
            resp.sendRedirect("/cashregister/acc");
            return;
        }
        if (updateduser.getId() == user.getId()) {
                storeLoginedUser(req.getSession(), updateduser);

        }
        resp.sendRedirect("/cashregister/acc");
    }
}
