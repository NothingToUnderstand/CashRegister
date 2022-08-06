package com.example.cashregister.controller.receipt;


import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add/product")
public class AddProductsToReceipt extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddProductsToReceipt.class);
    private final ReceiptDao receiptDao;

    public AddProductsToReceipt() {
        this.receiptDao = new ReceiptDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (receiptDao.addProductToReceipt(
                Integer.parseInt(req.getParameter("receiptId")),
                Integer.parseInt(req.getParameter("productId")),
                Integer.parseInt(req.getParameter("quantity")))) {
            log.info("product with id: " + req.getParameter("productId") + " added");
            req.getSession().setAttribute("message", "product with id: " + req.getParameter("productId") + " added");
        } else {
            req.getSession().setAttribute("errormessage", "product with id: " + req.getParameter("productId") + " not added");
            log.info("product with id: " + req.getParameter("productId") + " not added");
        }
        resp.sendRedirect("/all/products");
    }
}
