package com.example.cashregister.controller.account;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.Service.extra.SortingAndPagination;
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

@WebServlet(name = "Acc",value = "/acc")
public class Account extends HttpServlet {
    private static final Logger log = Logger.getLogger(Account.class);
    @Inject
    private ServiceAbstractFactory service;
    private int receiptId = 0;
    private Receipt receipt;
    SortingAndPagination sort;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            switch (getLoginedUser(req.getSession()).getRole()) {
                case "admin" -> {
                    log.info("admin acc");
                    sort = new SortingAndPagination(service.createUserService());
                    setAtr(req);
                    getServletContext().getRequestDispatcher("/forAdmin/admin.jsp").forward(req, resp);
                }
                case "commodity_expert" -> {
                    log.info("commodity_expert acc");
                    sort = new SortingAndPagination(service.createProductService());
                    setAtr(req);
                    getServletContext().getRequestDispatcher("/forCommodityExpert/commodity_expert.jsp").forward(req, resp);
                }
                case "senior_cashier" -> {
                    log.info("senior_cashier acc");
                    sort = new SortingAndPagination(service.createReceiptService());
                    setAtr(req);
                    getServletContext().getRequestDispatcher("/forSeniorCashier/senior_cashier.jsp").forward(req, resp);
                }
                case "cashier"->{
                    if (req.getParameter("id") != null) {
                        try {
                            receiptId=Integer.parseInt(req.getParameter("id"));
                        }catch (NumberFormatException e){
                            resp.sendRedirect("/cashregister/error");
                        }

                    }
                    receipt = service.createReceiptService().get(String.valueOf(receiptId));
                    if (receipt!=null&&receipt.getCloseDate() != null) {
                        receipt=null;
                        receiptId=0;
                    }
                    req.setAttribute("receipt", receipt);
                    req.setAttribute("user", getLoginedUser(req.getSession()));
                    getServletContext().getRequestDispatcher("/forCashier/cashier.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            resp.sendRedirect("/cashregister/error");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            switch (getLoginedUser(req.getSession()).getRole()) {
                case "admin" -> {
                    log.info("admin acc");
                    req.setAttribute("search", service.createUserService().searchUser(req.getParameter("fullname")));
                }
                case "commodity_expert" -> {
                    log.info("commodity_expert acc");
                    req.setAttribute("search", service.createProductService().searchProduct(req.getParameter("name")));
                }
                case "senior_cashier" -> {
                    log.info("senior_cashier acc");
                    req.setAttribute("search", service.createReceiptService().get(req.getParameter("id")));
                }

            }
            doGet(req, resp);
        } catch (SQLException e) {
            resp.sendRedirect("/cashregister/error");
        }
    }


    private String changeDir(String dir) {
        return dir.equals("ASC") ? "DESC" : "ASC";
    }

    private void setAtr(HttpServletRequest req) throws SQLException {

        sort.setParams(
                req.getParameter("col"),
                req.getParameter("dir"),
                req.getParameter("page"),
                req.getParameter("perpage"));


        req.setAttribute("dir", changeDir(sort.getDir()));
        req.setAttribute("col", sort.getColumn());
        req.setAttribute("perpage", sort.getPerpage());
        req.setAttribute("page", sort.getPage());
        req.setAttribute("amount", sort.getAmount());
        req.setAttribute("numpage", sort.getNumberOfPages());
        req.setAttribute("user", getLoginedUser(req.getSession()));
        req.setAttribute("items", sort.getList());
    }

}
