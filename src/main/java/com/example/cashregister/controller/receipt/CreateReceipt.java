package com.example.cashregister.controller.receipt;

import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import com.example.cashregister.entity.User;
import com.example.cashregister.security.UserSession;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create/receipt")
public class CreateReceipt extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateReceipt.class);
    private final ReceiptDao receiptDao;

    public CreateReceipt() {
        this.receiptDao=new ReceiptDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Create receipt");
        User user=UserSession.getLoginedUser(req.getSession());
        int id = receiptDao.createReceipt(user.getId(), user.getFullName());
        if (id != 0) {
            log.info("Receipt created with id: " + id);
            req.getSession().setAttribute("message", "New receipt created with id: " + id);
            resp.addIntHeader("receiptid",id);
        } else {
            log.warn("Receipt wasn't created ");
            req.getSession().setAttribute("errormessage", "New receipt wasn't created");
        }
        resp.sendRedirect("/acc?id="+id);
    }

}