package com.example.cashregister.controller.account.strategy;

import com.example.cashregister.Service.abstractFactory.ServiceAbstractFactory;
import com.example.cashregister.Service.extra.SortingAndPagination;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.example.cashregister.security.UserSession.getLoginedUser;

public abstract class Acc<T> {
    protected ServiceAbstractFactory serviceAbstractFactory;

    public Acc(ServiceAbstractFactory serviceAbstractFactory) {
        this.serviceAbstractFactory = serviceAbstractFactory;
    }

    SortingAndPagination sort ;

    abstract protected void  setSort();
    abstract T post(String param) throws SQLException;
    public   HttpServletRequest get(HttpServletRequest req,String col,String dir,String page,String perpage) throws SQLException{
        sort.setParams(col, dir, page, perpage);
        req.setAttribute("dir", changeDir(sort.getDir()));
        req.setAttribute("col", sort.getColumn());
        req.setAttribute("perpage", sort.getPerpage());
        req.setAttribute("page", sort.getPage());
        req.setAttribute("amount", sort.getAmount());
        req.setAttribute("numpage", sort.getNumberOfPages());
        req.setAttribute("items", sort.getList());
        return req;
    };
    protected String changeDir(String dir) {
        return dir.equals("ASC") ? "DESC" : "ASC";
    }
}
