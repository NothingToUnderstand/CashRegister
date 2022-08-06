package com.example.cashregister.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import static com.example.cashregister.property.Properties.getProperty;

/**
 * connection pool for this project
 * */
public class ApacheConPool{
    private final static BasicDataSource dataSource=new BasicDataSource();
    private ApacheConPool(){}
    static {
        dataSource.setUrl(getProperty("url"));
        dataSource.setUsername(getProperty("username"));
        dataSource.setPassword(getProperty("password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(15);
    }

    public static Connection getConnection() throws SQLException {
         return  dataSource.getConnection();
    }
}
