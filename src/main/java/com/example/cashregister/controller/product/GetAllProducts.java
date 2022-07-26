package com.example.cashregister.controller.product;

import com.example.cashregister.dao.ProductDAO;
import org.apache.log4j.Logger;

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
    private static final Logger log = Logger.getLogger(GetAllProducts.class);
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Get all users");
        req.setAttribute("products",ProductDAO.getAllProducts() );
        getServletContext().getRequestDispatcher("/product/allproducts.jsp").forward(req,resp);
    }

}
