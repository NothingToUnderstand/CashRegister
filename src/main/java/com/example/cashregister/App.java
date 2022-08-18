package com.example.cashregister;


import com.example.cashregister.Service.SortingAndPagination;
import com.example.cashregister.dao.abstractFactory.DaoAbstractFactoryImpl;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.dao.impl.UserDaoImpl;

public class App {
    public static void main(String[] args)  {
        System.out.println(new DaoAbstractFactoryImpl().createUserDao().searchUser("Tom Cat"));
    }
}