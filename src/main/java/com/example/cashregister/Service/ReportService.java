package com.example.cashregister.Service;

import com.example.cashregister.entity.Report;

import java.sql.SQLException;

public interface ReportService extends MainService<Report>{
    int createReport(int cashierId, String cashierName, boolean zReport) throws SQLException;
}
