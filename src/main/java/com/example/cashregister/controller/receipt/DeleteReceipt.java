package com.example.cashregister.controller.receipt;

import com.example.cashregister.controller.product.GetProduct;
import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete receipt servlet
 * */
@WebServlet("/delete/receipt")
public class DeleteReceipt extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeleteReceipt.class);
    private final ReceiptDao receiptDao;

    public DeleteReceipt() {
        this.receiptDao=new ReceiptDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Servlet to delete receipt");
        if (receiptDao.deleteReceipt(Integer.parseInt(request.getParameter("id")))) {
            log.info("Delete success");
            request.getSession().setAttribute("message","Receipt with id:"+request.getParameter("id")+" was deleted");
            response.sendRedirect("/acc/senior_cashier");
        } else {
            log.warn("Delete failed");
            request.getSession().setAttribute("errormessage","Receipt wasn't deleted");
        }

    }
}
