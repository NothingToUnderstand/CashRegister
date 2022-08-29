package com.example.cashregister.controller.account.strategy;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.Service.extra.SortingAndPagination;
import com.example.cashregister.entity.Product;

import java.sql.SQLException;

public class CommodityExpertAcc extends Acc<Product> {


    public CommodityExpertAcc(ServiceAbstractFactory serviceAbstractFactory) {
        super(serviceAbstractFactory);
    }


    @Override
    public void setSort() {
        super.sort = new SortingAndPagination(this.serviceAbstractFactory.createProductService());
    }

    @Override
    public Product post(String param) throws SQLException {
        return this.serviceAbstractFactory.createProductService().searchProduct(param);
    }
}