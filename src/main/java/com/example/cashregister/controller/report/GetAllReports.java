package com.example.cashregister.controller.report;


import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.Service.extra.SortingAndPagination;
import com.example.cashregister.entity.Report;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/all/reports")
public class GetAllReports extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetAllReports.class);
    @Inject
    private ServiceAbstractFactory service;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SortingAndPagination sort = new SortingAndPagination(service.createReportService());
        try {
        sort.setParams(
                req.getParameter("col"),
                req.getParameter("dir"),
                req.getParameter("page"),
                req.getParameter("perpage"));

            req.setAttribute("dir", changeDir(sort.getDir()));
            req.setAttribute("col", sort.getColumn());
            req.setAttribute("perpage", sort.getPerpage());
            req.setAttribute("page", sort.getPage());
            req.setAttribute("amount", sort.getAmount());
            req.setAttribute("numpage", sort.getNumberOfPages());
            req.setAttribute("reports", sort.getList());
        } catch (SQLException | NumberFormatException e) {
            log.error("error during getting all reports");
            resp.sendRedirect("/cashregister/error");
        }
        getServletContext().getRequestDispatcher("/forAdmin/allreports.jsp").forward(req, resp);
    }

    private String changeDir(String dir) {
        return dir.equals("ASC") ? "DESC" : "ASC";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                req.setAttribute("search",service.createReportService().get(req.getParameter("id")));
            } catch (SQLException e) {
                log.error("error during report searching",e);
                resp.sendRedirect("/cashregister/error");
            }

        doGet(req, resp);
    }
}