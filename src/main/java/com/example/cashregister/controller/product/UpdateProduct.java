package com.example.cashregister.controller.product;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.entity.Product;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Update product servlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,//2mb
        maxFileSize = 1024 * 1024 * 2,//2mb
        maxRequestSize = 1024 * 1024 * 10)//10mb
@WebServlet(name = "UpdateProduct", value = "/update/product")
public class UpdateProduct extends HttpServlet {
    private static final Logger log = Logger.getLogger(UpdateProduct.class);
    @Inject
    private ServiceAbstractFactory service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet,update product");
        Product product = null;
        try {
            product = service.createProductService().get(req.getParameter("id"));
        } catch (SQLException e) {
            log.error("error during updating product", e);
            resp.sendRedirect("/cashregister/error");
        }
        if (product != null) {
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/forCommodityExpert/updateproduct.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("errormessage", "There is no such product");
            resp.sendRedirect("/cashregister/acc");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost,update product");
        try {
            if (service.createProductService().updateProduct(
                    req.getParameter("id"),
                    req.getParameter("name"),
                    req.getParameter("quantity"),
                    req.getParameter("weight"),
                    req.getParameter("price"),
                    req.getPart("img"))) {
                log.info("Update is successfully");
                req.getSession().setAttribute("message", "Product updated");
            } else {
                log.warn("Update is failed");
                req.getSession().setAttribute("errormessage", "Product wasn't updated");
            }
            resp.sendRedirect("/cashregister/acc");
        } catch (SQLException e) {
            log.error("error during updating product", e);
            resp.sendRedirect("/cashregister/error");
        }
    }
}
