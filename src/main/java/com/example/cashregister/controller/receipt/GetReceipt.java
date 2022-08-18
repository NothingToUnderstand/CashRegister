package com.example.cashregister.controller.receipt;

import com.example.cashregister.controller.product.GetProduct;

import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Getting receipt servlet
* */
@WebServlet("/info/receipt")
public class GetReceipt extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetProduct.class);
    private final ReceiptDao receiptDao;

    private int receiptId = 0;
    public GetReceipt() {
        this.receiptDao=new ReceiptDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            receiptId=Integer.parseInt(request.getParameter("id"));
        }
        request.setAttribute("receipt", receiptDao.get(receiptId));
        request.getSession().setAttribute("message","Getting receipt with id: "+receiptId);
        getServletContext().getRequestDispatcher("/forSeniorCashier/inforeceipt.jsp").forward(request,response);
    }
}
