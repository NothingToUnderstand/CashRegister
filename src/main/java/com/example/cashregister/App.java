package com.example.cashregister;

import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.impl.ProductDaoImpl;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import com.example.cashregister.dao.impl.ReportDaoImpl;
import com.example.cashregister.dao.impl.UserDaoImpl;

import java.sql.SQLException;

import static com.example.cashregister.connection.ApacheConPool.getConnection;

public class App {
    public static void main(String[] args)  {
        System.out.println(new ReceiptDaoImpl().cancelProduct(3,2,90,9));
    }
}