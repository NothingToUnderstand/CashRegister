package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.ReportService;
import com.example.cashregister.dao.ReportDao;
import com.example.cashregister.entity.Report;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportServiceImpl implements ReportService {
    private ReportDao reportDao;
    @Inject
    public ReportServiceImpl(ReportDao reportDao){
        this.reportDao=reportDao;
    }
    private static final Logger log = Logger.getLogger(ReportServiceImpl.class);

    @Override
    public ArrayList<Report> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException {
        if(column==null||direction==null||limitfrom==null||limitquantity==null){
            throw new NumberFormatException();
        }
        return this.reportDao.getAll(column, direction, limitfrom, limitquantity);
    }

    @Override
    public int countRows() throws SQLException {
        return this.reportDao.countRows();
    }

    @Override
    public Report get(String idReport) throws SQLException {
        int id;
        try {
            id = Integer.parseInt(idReport);
        } catch (NumberFormatException e) {
            log.warn("number format exception", e);
          return null;
        }
        return this.reportDao.get(id);
    }

    @Override
    public Integer createReport(Integer cashierId, String cashierName, Boolean zReport) throws SQLException{
        if(cashierId==null||cashierName==null||zReport==null){
            throw new NumberFormatException();
        }
        return this.reportDao.createReport(cashierId, cashierName, zReport);
    }
}
