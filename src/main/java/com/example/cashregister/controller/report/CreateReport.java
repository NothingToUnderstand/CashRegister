package com.example.cashregister.controller.report;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.controller.receipt.CreateReceipt;
import com.example.cashregister.dao.ReportDao;
import com.example.cashregister.dao.impl.ReportDaoImpl;
import com.example.cashregister.entity.Report;
import com.example.cashregister.entity.User;
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
import static com.example.cashregister.security.UserSession.getLoginedUser;

@WebServlet("/created/report")
public class CreateReport extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateReceipt.class);
    @Inject
    private ServiceAbstractFactory service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Report report = null;
        try {
            report = service.createReportService().get(req.getParameter("id"));
        } catch (SQLException e) {
            log.error("error during getting report");
            resp.sendRedirect("/cashregister/error");
        } catch (NumberFormatException e) {
            log.error("error during getting report");
          //  req.getSession().setAttribute("errormessage", "id is not valid");
            setErrormessage("id is not valid");
            resp.sendRedirect("/cashregister/acc");
        }
        if (report != null) {
            req.setAttribute("report", report);
        } else {
//            req.getSession().setAttribute("errormessage", "There is no such report");
            setErrormessage("There is no such report");
        }

        getServletContext().getRequestDispatcher("/forSeniorCashier/createdreport.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getLoginedUser(req.getSession());
        int id = 0;
        if(req.getParameter("type")==null){
            setErrormessage("Type can not be null");
            resp.sendRedirect("/cashregister/acc");
        }
        try {
            if (req.getParameter("type").equals("X")) {
                id = service.createReportService().createReport(user.getId(), user.getFullName(), false);
            } else{
                id = service.createReportService().createReport(user.getId(), user.getFullName(), true);
            }
        }catch (SQLException e){
            log.error("Error during creating report",e);
            resp.sendRedirect("/cashregister/error");
        }
        if (id != 0) {
            setMessage("report "+req.getParameter("type")+" created with id: " + id);
            req.setAttribute("id", id);
        } else {
            setErrormessage("report was not created");
            resp.sendRedirect("/acc/senior_cashier");
        }
        resp.sendRedirect("/cashregister/created/report?id=" + id);
    }
}
