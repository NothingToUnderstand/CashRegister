package com.example.cashregister.controller.receipt;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.controller.product.GetProduct;
import com.example.cashregister.entity.Receipt;
import org.apache.log4j.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.cashregister.Service.extra.Notifications.setErrormessage;

/**
 * Getting receipt servlet
* */
@WebServlet("/info/receipt")
public class GetReceipt extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetProduct.class);
    @Inject
    private ServiceAbstractFactory service;
    Receipt receipt= null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getParameter("id")!=null){
            receipt = service.createReceiptService().get(request.getParameter("id"));
            }
        } catch (SQLException e) {
            response.sendRedirect("/cashregister/error");
        }catch (NumberFormatException e){
            setErrormessage("id is not valid");
            response.sendRedirect("/cashregister/acc");
        }
        if(receipt!=null){
            request.setAttribute("receipt", receipt);
            getServletContext().getRequestDispatcher("/forSeniorCashier/inforeceipt.jsp").forward(request,response);
        }else{
            setErrormessage("There is no such receipt");
            response.sendRedirect("/cashregister/acc");
        }

    }
}
