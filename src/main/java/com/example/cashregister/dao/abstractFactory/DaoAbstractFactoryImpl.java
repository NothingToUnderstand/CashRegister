
package com.example.cashregister.dao.abstractFactory;


import com.example.cashregister.dao.*;
import com.example.cashregister.dao.impl.*;

public class DaoAbstractFactoryImpl implements DaoAbstractFactory {

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public ProductDao createProductDao() {
        return new ProductDaoImpl();
    }

    @Override
    public ReceiptDao createReceiptDao() {
        return new ReceiptDaoImpl();
    }

    @Override
    public ReportDao createReportDao() {
        return new ReportDaoImpl();
    }

    @Override
    public ArchiveReceiptDao createArchiveReceiptDao() {
        return new ArchiveReceiptDaoImpl();
    }
}

