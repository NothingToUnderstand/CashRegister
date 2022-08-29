package com.example.cashregister.controller.account.strategy;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.Service.extra.SortingAndPagination;
import com.example.cashregister.entity.User;

import java.sql.SQLException;

public class AdminAcc extends Acc<User>{

    public AdminAcc(ServiceAbstractFactory serviceAbstractFactory) {
        super(serviceAbstractFactory);
    }

    @Override
    public void setSort() {
      super.sort=new SortingAndPagination(this.serviceAbstractFactory.createUserService());
    }

    @Override
    public User post(String param) throws SQLException {
        return this.serviceAbstractFactory.createUserService().searchUser(param);
    }
}
