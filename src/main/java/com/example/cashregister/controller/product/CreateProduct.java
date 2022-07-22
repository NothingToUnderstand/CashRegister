package com.example.cashregister.controller.product;



import com.example.cashregister.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Create product servlet
 * */
@WebServlet("/create/product")
public class CreateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/product/createproduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = ProductDAO.createProduct((String) req.getParameter("name"),
                Integer.parseInt(req.getParameter("quantity")),
                Double.parseDouble(req.getParameter("weight")),
                Double.parseDouble(req.getParameter("price")));
        if(id!=0){
            resp.sendRedirect("/all/products");
        }else{
            // TODO: 15.07.2022 how to show message create success or not +id
        }
    }
}
