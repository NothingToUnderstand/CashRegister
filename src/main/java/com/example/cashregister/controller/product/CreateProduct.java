package com.example.cashregister.controller.product;


import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create product servlet
 */
@MultipartConfig(fileSizeThreshold=1024*1024*2,//2mb
        maxFileSize=1024*1024*2,//2mb
        maxRequestSize=1024*1024*10)//10mb
@WebServlet("/create/product")
public class CreateProduct extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateProduct.class);
    private final ProductDao productDao;

    public CreateProduct() {
        this.productDao=new ProductDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Servlet doGet");
        getServletContext().getRequestDispatcher("/product/createproduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CreateProduct Servlet doPost");
        int id = productDao.createProduct((String) req.getParameter("name"),
                Integer.parseInt(req.getParameter("quantity")),
                Double.parseDouble(req.getParameter("weight")),
                Double.parseDouble(req.getParameter("price")),
                req.getPart("img").getInputStream().readAllBytes());
        if (id != 0) {
            log.info("Product created with id: " + id);
            req.getSession().setAttribute("message", "New product created with id: " + id);
            resp.sendRedirect("/acc/commodity_expert");
        } else {
            log.warn("Product wasn't created ");
            req.getSession().setAttribute("errormessage", "New product wasn't created");
        }
    }
}
