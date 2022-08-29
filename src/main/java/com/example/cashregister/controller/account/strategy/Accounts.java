package com.example.cashregister.controller.account.strategy;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Accounts {
    Acc<?> acc;

    public void setAcc(Acc<?> acc) {
        this.acc = acc;
        this.acc.setSort();
    }


    public HttpServletRequest get(HttpServletRequest req,String col,String dir,String page,String perpage) throws SQLException {
        return acc.get(req,col,dir,page,perpage);
    }
    public Object post(String param) throws SQLException {
        return acc.post(param);
    }
}
