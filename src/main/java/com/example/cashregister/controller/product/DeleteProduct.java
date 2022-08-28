package com.example.cashregister.controller.product;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Delete product servlet
 * */
@WebServlet("/delete/product")
public class DeleteProduct extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeleteProduct.class);
    @Inject
    private ServiceAbstractFactory service;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (service.createProductService().deleteProduct(request.getParameter("id"))) {
                log.info("Delete success");
                request.getSession().setAttribute("message","Product with id:"+request.getParameter("id")+" was deleted");
            } else {
                log.warn("Delete failed");
                request.getSession().setAttribute("errormessage","Product wasn't deleted");
            }
        } catch (SQLException e) {
            log.error("error during removing product",e);
            response.sendRedirect("/cashregister/error");
        }
        response.sendRedirect("/cashregister/acc");
    }
}
