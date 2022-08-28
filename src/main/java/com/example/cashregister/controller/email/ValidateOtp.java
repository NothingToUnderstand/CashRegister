package com.example.cashregister.controller.email;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateOtp
 */
@WebServlet("/validateOtp")
public class ValidateOtp extends HttpServlet {
    private int id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id= Integer.parseInt(req.getParameter("id"));
        getServletContext().getRequestDispatcher("/forgotPassword/EnterOtp.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String otp = (String) req.getSession().getAttribute("otp");
            String token = req.getParameter("token");
            if (otp.equals(token)) {
                req.getSession().setAttribute("message", "Otp token is valid");
                resp.sendRedirect("/cashregister/newPassword?id=" + id);
            } else {
                req.getSession().setAttribute("errormessage", "Otp token is invalid");
                resp.sendRedirect("/cashregister/");
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("/cashregister/");
        }
    }

}
