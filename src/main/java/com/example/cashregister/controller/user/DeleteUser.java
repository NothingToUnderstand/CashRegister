package com.example.cashregister.controller.user;


import com.example.cashregister.dao.UserDAO;

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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (UserDAO.deleteUser(Integer.parseInt(request.getParameter("id")))) {
            response.sendRedirect("/all/users");
        } else {
            // TODO: 15.07.2022 how to show message delete true or false

        }

    }
}