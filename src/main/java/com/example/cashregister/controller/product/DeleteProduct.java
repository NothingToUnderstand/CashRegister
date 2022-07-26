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
 * Delete product servlet
 * */
@WebServlet("/delete/product")
public class DeleteProduct extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeleteProduct.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Servlet to delete user");
        if (ProductDAO.deleteProduct(Integer.parseInt(request.getParameter("id")))) {
            log.info("Delete success");
            request.getSession().setAttribute("message","Product with id:"+request.getParameter("id")+" was deleted");
            response.sendRedirect("/acc/commodity_expert");
        } else {
            log.warn("Delete failed");
            request.getSession().setAttribute("errormessage","Product with wasn't deleted");
        }

    }
}
