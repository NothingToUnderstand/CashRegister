package com.example.cashregister.controller.account;


import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.cashregister.security.UserSession.getLoginedUser;

@WebServlet(name = "SeniorCashierAcc", value = "/acc/senior_cashier")
public class SeniorCashier extends HttpServlet {
    private static final Logger log = Logger.getLogger(CommodityExpert.class);
    private final ReceiptDao receiptDao;

    public SeniorCashier() {
        this.receiptDao = new ReceiptDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getLoginedUser(req.getSession());
        req.setAttribute("user", user);

        req.setAttribute("dir", "ASC");
        req.setAttribute("page", 1);
        req.setAttribute("perpage", 5);
        req.setAttribute("col", "id");


        String dir = (String) req.getAttribute("dir");
        int page = (int) req.getAttribute("page");
        int perpage = (int) req.getAttribute("perpage");
        String col = (String) req.getAttribute("col");

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("page", page);

        }
        if (req.getParameter("perpage") != null) {
            perpage = Integer.parseInt(req.getParameter("perpage"));
            req.setAttribute("perpage", perpage);
        }

        if (req.getParameter("dir") != null) {
            dir = req.getParameter("dir");
            req.setAttribute("dir", changeDir(req.getParameter("dir")));
        }
        if (req.getParameter("col") != null) {
            col = req.getParameter("col");
            req.setAttribute("col", col);
        }

        int amount = receiptDao.countRows();
        int limto = page * perpage;
        int limfrom = limto - perpage;
        int amountpages = amount / perpage;

        req.setAttribute("receipt", receiptDao.getAllReceipts(col, dir, limfrom, limto));
        req.setAttribute("numpage", amountpages);
        req.setAttribute("amount", amount);
        getServletContext().getRequestDispatcher("/forSeniorCashier/senior_cashier.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("search", receiptDao.getReceipt(Integer.parseInt(req.getParameter("id"))));
        doGet(req, resp);
    }

    private String changeDir(String dir) {
        return dir.equals("ASC") ? "DESC" : "ASC";
    }
}
