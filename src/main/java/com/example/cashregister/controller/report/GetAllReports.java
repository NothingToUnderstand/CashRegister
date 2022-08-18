package com.example.cashregister.controller.report;


import com.example.cashregister.Service.SortingAndPagination;
import com.example.cashregister.dao.ReportDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.dao.impl.ReportDaoImpl;
import com.example.cashregister.entity.Report;
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
        SortingAndPagination sort= new SortingAndPagination(reportDao);
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
        req.setAttribute("reports",sort.getList());
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
            req.setAttribute("search", reportDao.get(Integer.parseInt(req.getParameter("id"))));
        }
        doGet(req, resp);
    }
}