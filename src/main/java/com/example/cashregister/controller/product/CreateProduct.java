package com.example.cashregister.controller.product;


import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
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

import static com.example.cashregister.Service.extra.Notifications.setErrormessage;
import static com.example.cashregister.Service.extra.Notifications.setMessage;

/**
 * Create product servlet
 */
@MultipartConfig(fileSizeThreshold=1024*1024*2,//2mb
        maxFileSize=1024*1024*2,//2mb
        maxRequestSize=1024*1024*10)//10mb
@WebServlet("/create/product")
public class CreateProduct extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateProduct.class);
    @Inject
    private ServiceAbstractFactory service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/forCommodityExpert/createproduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        try {
            id = service.createProductService().createProduct(
                    req.getParameter("name"),
                    req.getParameter("quantity"),
                    req.getParameter("weight"),
                    req.getParameter("price"),
                    req.getPart("img").getInputStream().readAllBytes());
        } catch (SQLException e) {
            log.error("error during product creation",e);
            resp.sendRedirect("/cashregister/error");
        }

        if (id != 0) {
            log.info("Product created with id: " + id);
            setMessage("New product created with id: " + id);
            resp.sendRedirect("/cashregister/acc");
        } else {
            log.warn("Product wasn't created ");
            setErrormessage("New product wasn't created");
        }
    }
}
