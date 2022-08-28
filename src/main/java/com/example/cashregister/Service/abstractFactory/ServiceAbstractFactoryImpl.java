package com.example.cashregister.Service.abstractFactory;


import com.example.cashregister.Service.*;
import com.example.cashregister.Service.Impl.*;
import com.example.cashregister.dao.abstractFactory.DaoAbstractFactory;

import javax.inject.Inject;


public class ServiceAbstractFactoryImpl implements ServiceAbstractFactory {
private DaoAbstractFactory daoAbstractFactory;
@Inject
public ServiceAbstractFactoryImpl(DaoAbstractFactory daoAbstractFactory) {
    this.daoAbstractFactory = daoAbstractFactory;
}
    @Override
    public UserService createUserService() {
        return new UserServiceImpl(daoAbstractFactory.createUserDao());
    }

    @Override
    public ProductService createProductService() {
        return new ProductServiceImpl(daoAbstractFactory.createProductDao());
    }

    @Override
    public ReceiptService createReceiptService() {
        return new ReceiptServiceImpl(daoAbstractFactory.createReceiptDao());
    }

    @Override
    public ReportService createReportService() {
        return new ReportServiceImpl(daoAbstractFactory.createReportDao());
    }

    @Override
    public ArchiveReceiptService createArchiveReceiptService() {
        return new ArchiveReceiptServiceImpl(daoAbstractFactory.createArchiveReceiptDao());
    }
}
