package com.example.cashregister.controller.product;

import com.example.cashregister.Service.extra.SortingAndPagination;
import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.entity.Product;
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
 * Get all products servlet
 */
@WebServlet("/all/products")
public class GetAllProducts extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetAllProducts.class);
    @Inject
    private ServiceAbstractFactory service;

    private int receiptid = 0;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SortingAndPagination sort = new SortingAndPagination(service.createProductService());

        sort.setParams(
                req.getParameter("col"),
                req.getParameter("dir"),
                req.getParameter("page"),
                req.getParameter("perpage"));
        try {
            req.setAttribute("dir", changeDir(sort.getDir()));
            req.setAttribute("col", sort.getColumn());
            req.setAttribute("perpage", sort.getPerpage());
            req.setAttribute("page", sort.getPage());
            req.setAttribute("amount", sort.getAmount());
            req.setAttribute("numpage", sort.getNumberOfPages());
            req.setAttribute("receipt", sort.getList());
        } catch (SQLException | NumberFormatException e) {
            log.error("error during getting all products");
            resp.sendRedirect("/cashregister/error");
        }
        if (req.getParameter("receiptid")!=null) {
            try {
                receiptid = Integer.parseInt(req.getParameter("receiptid"));
            } catch (NumberFormatException e) {
                log.error("number format exception", e);
                log.error("id is not valid",e);
                resp.sendRedirect("/all/products");
            }
        }
        req.setAttribute("receiptid", receiptid);
        getServletContext().getRequestDispatcher("/forCashier/allproducts.jsp").forward(req, resp);
    }

    private String changeDir(String dir) {
        return dir.equals("ASC") ? "DESC" : "ASC";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") != null) {
            try {
                Product product=service.createProductService().searchProduct(req.getParameter("name"));
                    req.setAttribute("search",product);
            } catch (SQLException e) {
                log.error("error during product searching",e);
                resp.sendRedirect("/cashregister/error");
            }
        }
        doGet(req, resp);
    }
}


