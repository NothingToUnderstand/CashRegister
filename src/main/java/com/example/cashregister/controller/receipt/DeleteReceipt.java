package com.example.cashregister.controller.receipt;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.controller.product.GetProduct;
import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
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
 * Delete receipt servlet
 */
@WebServlet("/delete/receipt")
public class DeleteReceipt extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeleteReceipt.class);
    @Inject
    private ServiceAbstractFactory service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (service.createReceiptService().deleteReceipt(request.getParameter("id"))) {
                log.info("Delete success");
                request.getSession().setAttribute("message", "Receipt with id:" + request.getParameter("id") + " was deleted");
            } else {
                log.warn("Delete failed");
                request.getSession().setAttribute("errormessage", "Receipt wasn't deleted");
            }
        } catch (SQLException e) {
            log.error("error during receipt removing", e);
            response.sendRedirect("/cashregister/error");
        } catch (NumberFormatException e) {
            log.error("error during receipt removing", e);
            request.getSession().setAttribute("errormessage", "Id is not valid");
            response.sendRedirect("/cashregister/acc");
        }
        response.sendRedirect("/cashregister/acc");
    }
}

