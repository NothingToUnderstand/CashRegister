package com.example.cashregister.controller.account;

import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import com.example.cashregister.entity.Receipt;
import com.example.cashregister.entity.User;
import com.example.cashregister.security.UserSession;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/acc/cashier")
public class Cashier extends HttpServlet {
    private static final Logger log = Logger.getLogger(Cashier.class);
    private final ReceiptDao receiptDao;
    private Receipt receipt;
    private int receiptId = 0;

    public Cashier() {
        this.receiptDao = new ReceiptDaoImpl();
        this.receipt = new Receipt();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserSession.getLoginedUser(req.getSession());
        if (req.getParameter("id") != null) {
            receiptId=Integer.parseInt(req.getParameter("id"));
        }
        receipt = receiptDao.getReceipt(receiptId);
        if (receipt.getCloseDate() != null) {
            receipt = new Receipt();
            receiptId=0;
        }

        req.setAttribute("receipt", receipt);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/forCashier/cashier.jsp").forward(req, resp);
    }

}


