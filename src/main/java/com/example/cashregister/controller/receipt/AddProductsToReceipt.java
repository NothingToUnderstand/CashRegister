package com.example.cashregister.controller.receipt;


import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.dao.ReceiptDao;
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

import static com.example.cashregister.Service.extra.Notifications.setErrormessage;
import static com.example.cashregister.Service.extra.Notifications.setMessage;

@WebServlet("/add/product")
public class AddProductsToReceipt extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddProductsToReceipt.class);
    @Inject
    private ServiceAbstractFactory service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (service.createReceiptService().addProductToReceipt(
                    req.getParameter("receiptId"),
                    req.getParameter("productId"),
                    req.getParameter("quantity"))) {
                log.info("product with id: " + req.getParameter("productId") + " added");
                setMessage("product with id: " + req.getParameter("productId") + " added");
            } else {
             setErrormessage("product with id: " + req.getParameter("productId") + " not added");
                log.info("product with id: " + req.getParameter("productId") + " not added");
            }
            resp.sendRedirect("/cashregister/all/products");
        } catch (SQLException e) {
            log.error("error during removing product in receipt");
            resp.sendRedirect("/cashregister/error");
        } catch (NumberFormatException e) {
            log.error("params are not valid");
            setErrormessage("params are not valid");
            resp.sendRedirect("/cashregister/all/products");
        }

    }
}
