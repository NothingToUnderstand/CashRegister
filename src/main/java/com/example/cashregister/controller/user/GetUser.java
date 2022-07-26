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
 * Get user servlet
 * */
@WebServlet(name="getUser",value="/info/user")
public class GetUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetUser.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet");
        request.setAttribute("user", UserDAO.getUser(Integer.parseInt(request.getParameter("id"))));
        getServletContext().getRequestDispatcher("/user/infouser.jsp").forward(request,response);
    }
}
