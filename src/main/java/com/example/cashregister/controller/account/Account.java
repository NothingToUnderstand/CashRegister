package com.example.cashregister.controller.account;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.controller.account.strategy.Accounts;
import com.example.cashregister.controller.account.strategy.AdminAcc;
import com.example.cashregister.controller.account.strategy.CommodityExpertAcc;
import com.example.cashregister.controller.account.strategy.SeniorCashierAcc;
import com.example.cashregister.entity.Receipt;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cashregister.security.UserSession.getLoginedUser;

@WebServlet(name = "Acc", value = "/acc")
public class Account extends HttpServlet {
    private static final Logger log = Logger.getLogger(Account.class);
    @Inject
    Accounts accounts;
    @Inject
    private ServiceAbstractFactory service;

    private int receiptId = 0;
    private Receipt receipt;
    private String jsp;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!role(req).equals("cashier")) {
            System.out.println(jsp);
            try {
                accounts.get(req, req.getParameter("col"),
                        req.getParameter("dir"),
                        req.getParameter("page"),
                        req.getParameter("perpage"));
            } catch (SQLException e) {
                log.error("error in get acc");
                resp.sendRedirect("/cashregister/error");
            }
        } else {
            if (req.getParameter("id") != null) {
                try {
                    receiptId = Integer.parseInt(req.getParameter("id"));
                    receipt = service.createReceiptService().get(String.valueOf(receiptId));
                } catch (NumberFormatException|SQLException e) {
                    resp.sendRedirect("/cashregister/error");
                }
            }
            if (receipt != null && receipt.getCloseDate() != null) {
                receipt = null;
                receiptId = 0;
            }
            req.setAttribute("receipt", receipt);
            req.setAttribute("user", getLoginedUser(req.getSession()));
        }
        getServletContext().getRequestDispatcher(jsp).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        role(req);
        try {
            req.setAttribute("search", accounts.post(req.getParameter("forsearch")));
        } catch (SQLException e) {
            log.error("error in post acc");
            resp.sendRedirect("/cashregister/error");
        }
        doGet(req, resp);
    }

    private String role(HttpServletRequest req) {
        String role = null;
        switch (getLoginedUser(req.getSession()).getRole()) {
            case "admin" -> {
                accounts.setAcc(new AdminAcc(service));
                jsp = "/forAdmin/admin.jsp";
                role="admin";
            }
            case "commodity_expert" -> {
                accounts.setAcc(new CommodityExpertAcc(service));
                jsp = "/forCommodityExpert/commodity_expert.jsp";
                role="commodity_expert";
            }
            case "senior_cashier" -> {
                accounts.setAcc(new SeniorCashierAcc(service));
                jsp = "/forSeniorCashier/senior_cashier.jsp";
                role="senior_cashier";
            }
            case "cashier" -> {
                jsp = "/forCashier/cashier.jsp";
                role="cashier";
            }
        }
        return role;
    }
}
