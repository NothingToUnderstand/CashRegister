package com.example.cashregister.controller.product;

import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
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
        log.info("Get all products");

        req.setAttribute("dir", "ASC");
        req.setAttribute("page", 1);
        req.setAttribute("perpage", 5);
        req.setAttribute("col", "name");


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

        int amount = productDao.countRows();
        int limto = page * perpage;
        int limfrom = limto - perpage;
        int amountpages = amount / perpage;

        req.setAttribute("products", productDao.getAllProducts(col, dir, limfrom, limto));
        req.setAttribute("numpage", amountpages);
        req.setAttribute("amount", amount);
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


