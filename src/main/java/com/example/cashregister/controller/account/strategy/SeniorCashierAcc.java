package com.example.cashregister.controller.account.strategy;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.Service.extra.SortingAndPagination;
import com.example.cashregister.entity.Receipt;
import java.sql.SQLException;


public class SeniorCashierAcc extends Acc<Receipt>{


    public SeniorCashierAcc(ServiceAbstractFactory serviceAbstractFactory) {
        super(serviceAbstractFactory);
    }


    @Override
    public void setSort() {
        super.sort=new SortingAndPagination(this.serviceAbstractFactory.createReceiptService());
    }

    @Override
    public Receipt post(String param) throws SQLException {
        return this.serviceAbstractFactory.createReceiptService().get(param);
    }
}