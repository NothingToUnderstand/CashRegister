package com.example.cashregister.dao.abstractFactory;


import com.example.cashregister.dao.*;

public interface DaoAbstractFactory {
    UserDao createUserDao();

    ProductDao createProductDao();

    ReceiptDao createReceiptDao();

    ReportDao createReportDao();

    ArchiveReceiptDao createArchiveReceiptDao();
}