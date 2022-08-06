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
 * Delete product servlet
 * */
@WebServlet("/delete/product")
public class DeleteProduct extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeleteProduct.class);
    private final ProductDao productDao;

    public DeleteProduct() {
        this.productDao=new ProductDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Servlet to delete product");
        if (productDao.deleteProduct(Integer.parseInt(request.getParameter("id")))) {
            log.info("Delete success");
            request.getSession().setAttribute("message","Product with id:"+request.getParameter("id")+" was deleted");
            response.sendRedirect("/acc/commodity_expert");
        } else {
            log.warn("Delete failed");
            request.getSession().setAttribute("errormessage","Product wasn't deleted");
        }

    }
}
