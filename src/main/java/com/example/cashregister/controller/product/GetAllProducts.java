package com.example.cashregister.controller.product;

import com.example.cashregister.Service.SortingAndPagination;
import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Get all products servlet
 */
@WebServlet("/all/products")
public class GetAllProducts extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetAllProducts.class);
    private final ProductDao productDao;

    public GetAllProducts() {
        this.productDao = new ProductDaoImpl();
    }

    private int receiptid = 0;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SortingAndPagination sort= new SortingAndPagination(productDao);
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
        req.setAttribute("receipt",sort.getList());
        if (req.getParameter("receiptid") != null) {
            receiptid = Integer.parseInt(req.getParameter("receiptid"));
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
            req.setAttribute("search", productDao.searchProduct(req.getParameter("name")));
        }
        doGet(req, resp);
    }
}


