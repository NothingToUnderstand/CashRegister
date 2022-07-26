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
 *Get product servlet
 * */
@WebServlet("/info/product")
public class GetProduct extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetProduct.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Get user");
        request.setAttribute("product", ProductDAO.getProduct(Integer.parseInt(request.getParameter("id"))));
        request.getSession().setAttribute("message","Getting product with id: "+request.getParameter("id"));
        getServletContext().getRequestDispatcher("/product/infoproduct.jsp").forward(request,response);
    }
}
