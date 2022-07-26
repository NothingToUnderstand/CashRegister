package com.example.cashregister.controller.account;

import com.example.cashregister.controller.security.UserSession;
import com.example.cashregister.dao.ProductDAO;
import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet for accounts
* */
@WebServlet(name = "account", value = "/acc/*")
public class Account extends HttpServlet {
    private static final Logger log = Logger.getLogger(Account.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = UserSession.getLoginedUser(request.getSession());
        System.out.println(user.getRole());
        switch (user.getRole()){
            case "unknown"->{
                request.getSession().setAttribute("errormessage","Please sing in to visit this");
                response.sendRedirect("/login");
            }
//            case "admin"->{
//            }
            case "commodity_expert"->{
                request.setAttribute("product", ProductDAO.getAllProducts());
            }
//            case "cashier"->{
//
//            }
//            case "senior_cashier"->{
//
//            }

        }
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/account/"+user.getRole()+".jsp").forward(request, response);
    }
}
