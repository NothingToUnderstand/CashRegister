package com.example.cashregister.controller.account;

import com.example.cashregister.Service.SortingAndPagination;
import com.example.cashregister.dao.abstractFactory.DaoAbstractFactory;
import com.example.cashregister.dao.abstractFactory.DaoAbstractFactoryImpl;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import com.example.cashregister.dao.impl.UserDaoImpl;
import com.example.cashregister.entity.Receipt;
import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.cashregister.security.UserSession.getLoginedUser;

@WebServlet(name = "Acc", value = "/acc")
public class Account extends HttpServlet {
    private static final Logger log = Logger.getLogger(Account.class);
    DaoAbstractFactory factory;
    private int receiptId = 0;
    private Receipt receipt;
    public Account(){
    this.factory=new DaoAbstractFactoryImpl();
    }

    SortingAndPagination sort;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (getLoginedUser(req.getSession()).getRole()){
            case "admin"->{
                log.info("admin acc");
                sort=new SortingAndPagination(new UserDaoImpl());
                setAtr(req);
                getServletContext().getRequestDispatcher("/forAdmin/admin.jsp").forward(req, resp);
            }
            case "commodity_expert"->{
                log.info("commodity_expert acc");
                sort=new SortingAndPagination(new ProductDaoImpl());
                setAtr(req);
                getServletContext().getRequestDispatcher("/forCommodityExpert/commodity_expert.jsp").forward(req, resp);
            }
            case "senior_cashier"->{
                log.info("senior_cashier acc");
                sort= new SortingAndPagination(new ReceiptDaoImpl());
                setAtr(req);
                getServletContext().getRequestDispatcher("/forSeniorCashier/senior_cashier.jsp").forward(req, resp);
            }
            case "cashier"->{
                if (req.getParameter("id") != null) {
                    receiptId=Integer.parseInt(req.getParameter("id"));
                }
                receipt = factory.createReceiptDao().get(receiptId);
                if (receipt.getCloseDate() != null) {
                    receipt = new Receipt();
                    receiptId=0;
                }

                req.setAttribute("receipt", receipt);
                req.setAttribute("user",  getLoginedUser(req.getSession()));
                getServletContext().getRequestDispatcher("/forCashier/cashier.jsp").forward(req, resp);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (getLoginedUser(req.getSession()).getRole()){
            case "admin"->{
                log.info("admin acc");
                req.setAttribute("search", factory.createUserDao().searchUser(req.getParameter("fullname")));
            }
            case "commodity_expert"->{
                log.info("commodity_expert acc");
                req.setAttribute("search",factory.createProductDao().searchProduct(req.getParameter("name")));
            }
            case "senior_cashier"->{
                log.info("senior_cashier acc");
                req.setAttribute("search", factory.createReceiptDao().get(Integer.parseInt(req.getParameter("id"))));
            }

        }
        doGet(req, resp);
    }


    private String changeDir(String dir) {
        return dir.equals("ASC") ? "DESC" : "ASC";
    }
    private HttpServletRequest setAtr(HttpServletRequest req){
        sort.setParams(
                req.getParameter("col"),
                req.getParameter("dir"),
                req.getParameter("page"),
                req.getParameter("perpage"));


        req.setAttribute("dir",changeDir(sort.getDir()));
        req.setAttribute("col", sort.getColumn());
        req.setAttribute("perpage", sort.getPerpage());
        req.setAttribute("page", sort.getPage());
        req.setAttribute("amount", sort.getAmount());
        req.setAttribute("numpage", sort.getNumberOfPages());
        req.setAttribute("user", getLoginedUser(req.getSession()));
        req.setAttribute("items",sort.getList());
        return req;
    }

}
