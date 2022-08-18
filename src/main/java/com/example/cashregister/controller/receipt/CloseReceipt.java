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

@WebServlet("/close/receipt")
public class CloseReceipt extends HttpServlet {
    private static final Logger log = Logger.getLogger(CloseReceipt.class);
    private final ReceiptDao receiptDao;

    public CloseReceipt() {
        this.receiptDao=new ReceiptDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Close receipt");
        int id=Integer.parseInt( req.getParameter("id"));
        if(receiptDao.closeReceipt(id)){
            log.info("Receipt is closed");
            req.getSession().setAttribute("message","receipt with id "+req.getParameter("id")+" is closed");
        }else{
            log.warn("Receipt is not closed");
            req.getSession().setAttribute("errormessage","receipt with id "+req.getParameter("id")+" is not closed");
        }
        resp.sendRedirect("/acc?id="+id);
    }
}
