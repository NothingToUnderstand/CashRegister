package com.example.cashregister.connection.notused;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * C3PO connection pool is not used it is working
* */
public class C3POconPool {
    public  static  ComboPooledDataSource comboPooledDataSource = null;

    static {
        comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cash_register?serverTimezone=UTC&allowPublicKeyRetrieval=true");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("Qwerty12345");

        comboPooledDataSource.setMinPoolSize(3);
        comboPooledDataSource.setAcquireIncrement(3);
        comboPooledDataSource.setMaxPoolSize(30);

    }
}
