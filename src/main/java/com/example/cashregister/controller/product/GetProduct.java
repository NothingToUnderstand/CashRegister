package com.example.cashregister.controller.product;

import com.example.cashregister.dao.ProductDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *Get product servlet
 * */
@WebServlet("/info/product")
public class GetProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("product", ProductDAO.getProduct(Integer.parseInt(request.getParameter("id"))));
        getServletContext().getRequestDispatcher("/product/infoproduct.jsp").forward(request,response);
    }
}
