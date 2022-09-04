package com.example.cashregister.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import static com.example.cashregister.Service.extra.Properties.getProperty;

/**
 * connection pool for this project
 * */
public class ApacheConPool{
    private static BasicDataSource dataSource;

    private ApacheConPool() {}

    //Singleton
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
        }
        init();
        return  dataSource.getConnection();
    }


    private static void  init() {
        dataSource.setUrl(getProperty("url"));
        dataSource.setUsername(getProperty("username"));
        dataSource.setPassword(getProperty("password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(15);
    }
}

