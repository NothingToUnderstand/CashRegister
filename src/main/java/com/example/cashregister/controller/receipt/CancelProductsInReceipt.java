package com.example.cashregister.controller.receipt;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
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

@WebServlet("/cancel/product")
public class CancelProductsInReceipt extends HttpServlet {

    private static final Logger log = Logger.getLogger(CancelProductsInReceipt.class);
    @Inject
    private ServiceAbstractFactory service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean status = false;
        try {
            if (Integer.parseInt(req.getParameter("quantity")) == Integer.parseInt(req.getParameter("atdb"))) {
                status = service.createReceiptService().deleteProductInReceipt(
                        req.getParameter("receiptid"),
                        req.getParameter("quantity"),
                        req.getParameter("number")
                );
            } else {
                status = service.createReceiptService().cancelProduct(
                        req.getParameter("number"),
                        req.getParameter("quantity"),
                        req.getParameter("receiptid"),
                        req.getParameter("productid")
                );
            }
        } catch (SQLException e) {
            log.error("error during removing product in receipt");
            resp.sendRedirect("/cashregister/error");
        } catch (NumberFormatException e) {
            log.error("params are not valid");
            setErrormessage("params are not valid");
            resp.sendRedirect("/cashregister/info/receipt");
        }
        if (status) {
            setMessage("delete is successfully");
        } else {
            setErrormessage("delete is failed");
        }

        resp.sendRedirect("/cashregister/info/receipt");
    }
}
