package com.example.cashregister.controller.user;


import com.example.cashregister.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Delete user servlet
 * */
@WebServlet(name="deleteUser",value="/delete/user")
public class DeleteUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteUser.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (UserDAO.deleteUser(Integer.parseInt(request.getParameter("id")))) {
            log.info("user deleted successfully");
            response.sendRedirect("/all/users");
        } else {
            log.warn("user wasn't deleted");
            // TODO: 15.07.2022 how to show message delete true or false

        }

    }
}