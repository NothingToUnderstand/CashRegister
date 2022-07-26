package com.example.cashregister.controller.product;

import com.example.cashregister.dao.ProductDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Update product servlet
 */
@MultipartConfig(fileSizeThreshold=1024*1024*2,//2mb
        maxFileSize=1024*1024*2,//2mb
        maxRequestSize=1024*1024*10)//10mb
@WebServlet(name="UpdateProduct",value="/update/product")
public class UpdateProduct extends HttpServlet {
    private static final Logger log = Logger.getLogger(UpdateProduct.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet,update product");
        getServletContext().getRequestDispatcher("/product/updateproduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost,update product");
        System.out.println("~~~~~~"+req.getParameter("id"));
        if (ProductDAO.updateProduct(
                Integer.parseInt(req.getParameter("id")),
                (String) req.getParameter("name"),
                Integer.parseInt(req.getParameter("quantity")),
                Double.parseDouble(req.getParameter("weight")),
                Double.parseDouble(req.getParameter("price")),
                req.getPart("img").getInputStream().readAllBytes())){

            log.info("Update is successfully");
            req.getSession().setAttribute("message", "Product updated");
            resp.sendRedirect("/acc/commodity_expert");
        } else{
            log.warn("Update is failed");
            req.getSession().setAttribute("errormessage", "Product wasn't updated");
        }
    }
}
