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
 * Update user servlet
 * */
@WebServlet(name="updateUser",value="/update/user")
public class UpdateUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(UpdateUser.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        getServletContext().getRequestDispatcher("/user/updateuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost");
        if(UserDAO.updateUser(Integer.parseInt(req.getParameter("id")),
                (String)req.getParameter("firstname"),
                (String)req.getParameter("lastname"),
                (String)req.getParameter("password"),
                Integer.parseInt(req.getParameter("roleid"))
                )){
            log.info("user was updated");
            resp.sendRedirect("/all/users");
        }else{
            log.warn("user wasn't updated");
            // TODO: 15.07.2022 how to show message create success or not +id
        }
    }
}
