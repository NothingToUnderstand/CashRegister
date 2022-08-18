package com.example.cashregister.controller.user;


import com.example.cashregister.dao.UserDao;
import com.example.cashregister.dao.impl.UserDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete user servlet
 */
@WebServlet(name = "deleteUser", value = "/delete/user")
public class DeleteUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteUser.class);
    private final UserDao userDao;

    public DeleteUser() {
        this.userDao=new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userDao.deleteUser(Integer.parseInt(request.getParameter("id")))) {
            log.info("user deleted successfully");
            request.getSession().setAttribute("message","User was removed");
        } else {
            log.warn("user wasn't deleted");
            request.getSession().setAttribute("message","User was not removed");
        }
        response.sendRedirect("/acc");
    }
}