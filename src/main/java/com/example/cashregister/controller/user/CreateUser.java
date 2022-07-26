package com.example.cashregister.controller.user;


import com.example.cashregister.controller.security.UserSession;
import com.example.cashregister.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create user servlet
 */
@WebServlet(name = "createUser", value = "/create/user")
public class CreateUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateUser.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        if (UserSession.getLoginedUser(req.getSession()).getId() != 0) {
            log.info("user has already logined");
            req.getSession().setAttribute("errormessage", "You are already had an account");
            resp.sendRedirect("/");
        } else {
            log.warn("user is not logined");
            getServletContext().getRequestDispatcher("/user/createuser.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        log.info("doPost");
        int id = UserDAO.createUser((String) req.getParameter("firstname"),
                (String) req.getParameter("lastname"),
                (String) req.getParameter("password"),
                Integer.parseInt(req.getParameter("roleid")));
        if (id != 0) {
            log.info("User was created with id: "+id);
            resp.sendRedirect("/login");
        } else {
            log.warn("user wasn't created");
            // TODO: 15.07.2022 how to show message create success or not +id
        }
    }
}
