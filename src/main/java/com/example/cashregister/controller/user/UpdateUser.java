package com.example.cashregister.controller.user;


import com.example.cashregister.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update user servlet
 * */
@WebServlet(name="updateUser",value="/update/user")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/user/updateuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(UserDAO.updateUser(Integer.parseInt(req.getParameter("id")),
                (String)req.getParameter("firstname"),
                (String)req.getParameter("lastname"),
                (String)req.getParameter("password"),
                Integer.parseInt(req.getParameter("roleid"))
                )){
            resp.sendRedirect("/all/users");
        }else{
            System.out.println("Not updated");
            // TODO: 15.07.2022 how to show message create success or not +id
        }
    }
}
