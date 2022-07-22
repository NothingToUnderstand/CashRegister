package com.example.cashregister.controller.product;

import com.example.cashregister.dao.ProductDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Delete product servlet
 * */
@WebServlet("/delete/product")
public class DeleteProduct extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ProductDAO.deleteProduct(Integer.parseInt(request.getParameter("id")))) {
            response.sendRedirect("/all/products");
        } else {
            // TODO: 15.07.2022 how to show message delete true or false

        }

    }
}
