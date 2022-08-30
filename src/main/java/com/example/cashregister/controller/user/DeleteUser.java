package com.example.cashregister.controller.user;


import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
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

/**
 * Delete user servlet
 */
@WebServlet(name = "deleteUser", value = "/delete/user")
public class DeleteUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteUser.class);
    @Inject
    private ServiceAbstractFactory service;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (service.createUserService().deleteUser(request.getParameter("id"))) {
                log.info("user deleted successfully");
                setMessage("User was removed");
            } else {
                log.warn("user wasn't deleted");
                request.getSession().setAttribute("errormessage", "User was not removed");
                setErrormessage("User was not removed");
            }
            response.sendRedirect("/cashregister/acc");
        } catch (SQLException e) {
            log.error("error during user removing", e);
            response.sendRedirect("/cashregister/error");
        } catch (NumberFormatException e) {
            log.error("error during user removing", e);
            request.getSession().setAttribute("errormessage", "Id is not valid");
            setErrormessage("Id is not valid");
            response.sendRedirect("/cashregister/acc");
        }
    }
}