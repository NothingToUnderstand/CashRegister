package com.example.cashregister.Service;

import com.example.cashregister.entity.Report;

import java.sql.SQLException;

public interface ReportService extends MainService<Report>{
    Integer createReport(Integer cashierId, String cashierName, Boolean zReport) throws SQLException;
}
