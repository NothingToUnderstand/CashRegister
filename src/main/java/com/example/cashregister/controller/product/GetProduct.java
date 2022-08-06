package com.example.cashregister.controller.product;

import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.security.UserSession;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *Get product servlet
 * */
@WebServlet("/info/product")
public class GetProduct extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetProduct.class);
    private final ProductDao productDao;

    public GetProduct() {
        this.productDao=new ProductDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Get product");
        request.setAttribute("user", UserSession.getLoginedUser(request.getSession()));
        request.setAttribute("product", productDao.getProduct(Integer.parseInt(request.getParameter("id"))));
        request.getSession().setAttribute("message","Getting product with id: "+request.getParameter("id"));
        getServletContext().getRequestDispatcher("/forCommodityExpert/infoproduct.jsp").forward(request,response);
    }
}
