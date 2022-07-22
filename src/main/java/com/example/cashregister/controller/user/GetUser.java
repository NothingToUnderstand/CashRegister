package com.example.cashregister.controller.user;


import com.example.cashregister.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Get user servlet
 * */
@WebServlet(value="/info/user")
public class GetUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", UserDAO.getUser(Integer.parseInt(request.getParameter("id"))));
        getServletContext().getRequestDispatcher("/user/infouser.jsp").forward(request,response);
    }
}
