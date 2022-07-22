package com.example.cashregister.controller.user;



import com.example.cashregister.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Create user servlet
 * */
@WebServlet(name="createUser",value="/create/user")
public class CreateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/user/createuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = UserDAO.createUser( (String)req.getParameter("firstname"),
                (String)req.getParameter("lastname"),
                (String)req.getParameter("password"),
                Integer.parseInt(req.getParameter("roleid")));
        if(id!=0){
            resp.sendRedirect("/all/users");
        }else{
            // TODO: 15.07.2022 how to show message create success or not +id
        }
    }
}
