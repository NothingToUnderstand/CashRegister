package com.example.cashregister.controller.report;

import com.example.cashregister.controller.receipt.CreateReceipt;
import com.example.cashregister.dao.ReportDao;
import com.example.cashregister.dao.impl.ReportDaoImpl;
import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.cashregister.security.UserSession.getLoginedUser;

@WebServlet("/created/report")
public class CreateReport extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateReceipt.class);
    private final ReportDao reportDao;

    public CreateReport() {
        this.reportDao=new ReportDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       req.setAttribute("report",reportDao.getReport(Integer.parseInt(req.getParameter("id"))));
       getServletContext().getRequestDispatcher("/forSeniorCashier/createdreport.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user= getLoginedUser(req.getSession());
        int id=0;
       if(req.getParameter("type").equals("X")){
       id=reportDao.createReport(user.getId(),user.getFullName(),false);;
       }else{
           id=reportDao.createReport(user.getId(),user.getFullName(),true);
       }
       if(id!=0){
           req.getSession().setAttribute("message","report created with id: "+id);
           req.setAttribute("id",id);
       }else{
           req.getSession().setAttribute("errormessage","report is not created");
           resp.sendRedirect("/acc/senior_cashier");
       }
        resp.sendRedirect("/created/report?id="+id);
    }
}
