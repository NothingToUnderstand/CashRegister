package com.example.cashregister.controller.report;


import com.example.cashregister.dao.ReportDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.dao.impl.ReportDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/all/reports")
public class GetAllReports extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetAllReports.class);
    private final ReportDao reportDao;

    public GetAllReports() {
        this.reportDao = new ReportDaoImpl();
    }

    private int reportid = 0;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Get all reports");

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

        int amount = reportDao.countRows();
        int limto = page * perpage;
        int limfrom = limto - perpage;
        int amountpages = amount / perpage;

        req.setAttribute("reports", reportDao.getAllReports(col, dir, limfrom, limto));
        req.setAttribute("numpage", amountpages);
        req.setAttribute("amount", amount);
        if (req.getParameter("reportid") != null) {
            reportid = Integer.parseInt(req.getParameter("reportid"));
        }
        req.setAttribute("reportid", reportid);
        getServletContext().getRequestDispatcher("/forAdmin/allreports.jsp").forward(req, resp);
    }

    private String changeDir(String dir) {
        return dir.equals("ASC") ? "DESC" : "ASC";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null&&(String)req.getParameter("id") !="") {
            req.setAttribute("search", reportDao.getReport(Integer.parseInt(req.getParameter("id"))));
        }
        doGet(req, resp);
    }
}