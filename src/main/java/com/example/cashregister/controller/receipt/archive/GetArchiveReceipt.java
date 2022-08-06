package com.example.cashregister.controller.receipt.archive;

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


@WebServlet("/info/archive/receipt")
public class GetArchiveReceipt extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetArchiveReceipt.class);
    private final ReceiptDao receiptDao;

    private int receiptId = 0;
    public GetArchiveReceipt() {
        this.receiptDao=new ReceiptDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            receiptId=Integer.parseInt(request.getParameter("id"));
        }
        request.setAttribute("receipt", receiptDao.getReceiptFromArchive(receiptId));
        request.getSession().setAttribute("message","Getting archive receipt with id: "+receiptId);
        getServletContext().getRequestDispatcher("/forAdmin/infoarchivereceipt.jsp").forward(request,response);
    }
}