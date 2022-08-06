package com.example.cashregister.dao;

import com.example.cashregister.entity.Report;

import java.util.List;

public interface ReportDao {
    int createReport(int cashierId, String cashierName, boolean zReport);

    Report getReport(int id);

    List<Report> getAllReports(String column, String direction, Integer limitfrom, Integer limitquantity);
    int countRows();
}
