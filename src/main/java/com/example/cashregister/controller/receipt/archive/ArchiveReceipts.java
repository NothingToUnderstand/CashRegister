package com.example.cashregister.controller.receipt.archive;

import com.example.cashregister.Service.SortingAndPagination;
import com.example.cashregister.dao.ArchiveReceiptDao;
import com.example.cashregister.dao.impl.ArchiveReceiptDaoImpl;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import com.example.cashregister.entity.Receipt;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/archive/receipts")
public class ArchiveReceipts extends HttpServlet {
    private static final Logger log = Logger.getLogger(ArchiveReceipts.class);
    private final ArchiveReceiptDao dao;

    public ArchiveReceipts() {
        this.dao = new ArchiveReceiptDaoImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SortingAndPagination sort= new SortingAndPagination(dao);
        sort.setParams(
                req.getParameter("col"),
                req.getParameter("dir"),
                req.getParameter("page"),
                req.getParameter("perpage"));

        req.setAttribute("dir",changeDir(sort.getDir()));
        req.setAttribute("col", sort.getColumn());
        req.setAttribute("perpage", sort.getPerpage());
        req.setAttribute("page", sort.getPage());
        req.setAttribute("amount", sort.getAmount());
        req.setAttribute("numpage", sort.getNumberOfPages());
        req.setAttribute("receipt",sort.getList());
        getServletContext().getRequestDispatcher("/forAdmin/archivereceipts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("search", dao.get(Integer.parseInt(req.getParameter("id"))));
        doGet(req, resp);
    }
    private String changeDir(String dir) {
        return dir.equals("ASC") ? "DESC" : "ASC";
    }
}

