package com.example.cashregister.controller.product;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.entity.Product;
import com.example.cashregister.security.UserSession;
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
 *Get product servlet
 * */
@WebServlet("/info/product")
public class GetProduct extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetProduct.class);
    @Inject
    private ServiceAbstractFactory service;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Get product");
        Product product= null;
        try {
            product = service.createProductService().get(request.getParameter("id"));
        } catch (SQLException e) {
            log.error("error during getting product");
            response.sendRedirect("/cashregister/error");
        }
        if(product!=null){
            request.setAttribute("product", product);
            request.getSession().setAttribute("message","Getting product with id: "+request.getParameter("id"));
        }else{
            request.getSession().setAttribute("errormessage","There is no such product");
        }
        request.setAttribute("user", UserSession.getLoginedUser(request.getSession()));
        getServletContext().getRequestDispatcher("/forCommodityExpert/infoproduct.jsp").forward(request,response);
    }
}
