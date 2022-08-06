package com.example.cashregister.controller.receipt;

import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cancel/product")
public class CancelProductsInReceipt extends HttpServlet {

    private static final Logger log = Logger.getLogger(CancelProductsInReceipt.class);
    private final ReceiptDao receiptDao;

    public CancelProductsInReceipt() {
        this.receiptDao=new ReceiptDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean status=false;

        if(Integer.parseInt(req.getParameter("quantity"))==Integer.parseInt(req.getParameter("atdb"))){
           status= receiptDao.deleteProductInReceipt(
                    Integer.parseInt(req.getParameter("receiptid")),
                    Integer.parseInt(req.getParameter("quantity")),
                    Integer.parseInt(req.getParameter("number"))
            );
        }else{
          status= receiptDao.cancelProduct(
                    Integer.parseInt(req.getParameter("number")),
                    Integer.parseInt(req.getParameter("quantity")),
                    Integer.parseInt(req.getParameter("receiptid")),
                    Integer.parseInt(req.getParameter("productid"))
            );
        }
        if(status){
            req.getSession().setAttribute("message","delete is successfully");
        }else{
            req.getSession().setAttribute("errormessage","delete is failed");
        }

        resp.sendRedirect("/info/receipt");
    }
}
