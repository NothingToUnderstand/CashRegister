package com.example.cashregister.controller.receipt;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.dao.ReceiptDao;
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

import static com.example.cashregister.Service.extra.Notifications.setErrormessage;
import static com.example.cashregister.Service.extra.Notifications.setMessage;

@WebServlet("/close/receipt")
public class CloseReceipt extends HttpServlet {
    private static final Logger log = Logger.getLogger(CloseReceipt.class);
    @Inject
    private ServiceAbstractFactory service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      boolean status;
        try {
           status= service.createReceiptService().closeReceipt(req.getParameter("id"));
        } catch (SQLException e) {
            resp.sendRedirect("/cashregister/error");
            return;
        }
        if(status){
            log.info("Receipt is closed");
            setMessage("receipt with id "+req.getParameter("id")+" is closed");
        }else{
            log.warn("Receipt is not closed");
            setErrormessage("receipt with id "+req.getParameter("id")+" is not closed");
        }
        resp.sendRedirect("/cashregister/acc");
    }
}
