package com.example.cashregister.controller.product;

import com.example.cashregister.dao.ProductDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Get all products servlet
 * */
@WebServlet("/all/products")
public class GetAllProducts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products",ProductDAO.getAllProducts() );
        getServletContext().getRequestDispatcher("/product/allproducts.jsp").forward(req,resp);
    }

}
