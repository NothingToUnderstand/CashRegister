package com.example.cashregister.controller.user;


import com.example.cashregister.dao.UserDao;
import com.example.cashregister.dao.impl.UserDaoImpl;
import com.example.cashregister.entity.User;
import com.example.cashregister.security.UserSession;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.cashregister.security.UserSession.getLoginedUser;
import static com.example.cashregister.security.UserSession.storeLoginedUser;

/**
 * Update user servlet
 */
@WebServlet(name = "updateUser", value = "/update/user")
public class UpdateUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(UpdateUser.class);
    private final UserDao userDao;

    public UpdateUser() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getLoginedUser(req.getSession());
        int id = Integer.parseInt(req.getParameter("id"));
        if (id != user.getId()&&!user.getRole().equals("admin")) {
            resp.sendRedirect("/");
            req.getSession().setAttribute("errormessage", "you can`t change another user");
            return;
        }
        req.setAttribute("user", userDao.get(id));
        getServletContext().getRequestDispatcher("/forAdmin/updateuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getLoginedUser(req.getSession());
        int id = Integer.parseInt(req.getParameter("id"));

        if (userDao.updateUser(id,
                req.getParameter("firstname"),
                req.getParameter("lastname"),
                req.getParameter("password"),
                req.getParameter("roleid"))) {
            log.info("user was updated");
            req.getSession().setAttribute("message", "user was updated");
            if (id == user.getId()) {
                storeLoginedUser(req.getSession(), userDao.get(id));
            }
        } else {
            log.warn("user wasn't updated");
            req.getSession().setAttribute("errormessage", "user was not updated");
        }

        resp.sendRedirect("/acc");
    }
}
