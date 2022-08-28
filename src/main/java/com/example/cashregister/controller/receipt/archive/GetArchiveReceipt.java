package com.example.cashregister.controller.receipt.archive;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.controller.product.GetProduct;
import com.example.cashregister.dao.ArchiveReceiptDao;
import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ArchiveReceiptDaoImpl;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
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


@WebServlet("/info/archive/receipt")
public class GetArchiveReceipt extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetArchiveReceipt.class);
    @Inject
    private ServiceAbstractFactory service;
    private int receiptId = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Receipt archiveReceipt= null;
        try {
            archiveReceipt = service.createArchiveReceiptService().get(req.getParameter("id"));
            if(archiveReceipt!=null){
                req.setAttribute("receipt", archiveReceipt);
                getServletContext().getRequestDispatcher("/forAdmin/infoarchivereceipt.jsp").forward(req,resp);
            }else{
                req.getSession().setAttribute("errormessage","There is no such receipt in archive");
                resp.sendRedirect("/cashregister/archive/receipts");
            }
        } catch (SQLException e) {
            log.error("error during getting an archive receipt");
            resp.sendRedirect("/cashregister/error");
        }catch (NumberFormatException e){
            log.error("params are not valid",e);
            req.getSession().setAttribute("errormessage","params are not valid");
            resp.sendRedirect("/cashregister/archive/receipts");
        }


    }
}