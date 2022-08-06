package com.example.cashregister.controller.receipt.archive;

import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.cashregister.security.UserSession.getLoginedUser;
@WebServlet("/archive/receipts")
public class ArchiveReceipts extends HttpServlet {
    private static final Logger log = Logger.getLogger(ArchiveReceipts.class);
    private final ReceiptDao receiptDao;

    public ArchiveReceipts() {
        this.receiptDao = new ReceiptDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dir", "ASC");
        req.setAttribute("page", 1);
        req.setAttribute("perpage", 5);
        req.setAttribute("col", "id");


        String dir = (String) req.getAttribute("dir");
        int page = (int) req.getAttribute("page");
        int perpage = (int) req.getAttribute("perpage");
        String col = (String) req.getAttribute("col");

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("page", page);

        }
        if (req.getParameter("perpage") != null) {
            perpage = Integer.parseInt(req.getParameter("perpage"));
            req.setAttribute("perpage", perpage);
        }

        if (req.getParameter("dir") != null) {
            dir = req.getParameter("dir");
            req.setAttribute("dir", changeDir(req.getParameter("dir")));
        }
        if (req.getParameter("col") != null) {
            col = req.getParameter("col");
            req.setAttribute("col", col);
        }

        int amount = receiptDao.countRowsArchive();
        int limto = page * perpage;
        int limfrom = limto - perpage;
        int amountpages = amount / perpage;

        req.setAttribute("receipt", receiptDao.getAllReceiptsFromArchive(col, dir, limfrom, limto));
        req.setAttribute("numpage", amountpages);
        req.setAttribute("amount", amount);
        getServletContext().getRequestDispatcher("/forAdmin/archivereceipts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("search", receiptDao.getReceiptFromArchive(Integer.parseInt(req.getParameter("id"))));
        doGet(req, resp);
    }

    private String changeDir(String dir) {
        return dir.equals("ASC") ? "DESC" : "ASC";
    }
}

