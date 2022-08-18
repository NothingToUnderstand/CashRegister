package com.example.cashregister.dao;

import com.example.cashregister.entity.Report;

import java.util.List;

public interface ReportDao extends MainDao<Report>{
    int createReport(int cashierId, String cashierName, boolean zReport);

}
