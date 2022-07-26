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
 * Get all users servlet
 * */
@WebServlet(name="allUsers",value = "/all/users")
public class GetAllUsers extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetAllUsers.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        req.setAttribute("users", UserDAO.getAllUsers() );
        getServletContext().getRequestDispatcher("/user/allusers.jsp").forward(req,resp);
    }

}