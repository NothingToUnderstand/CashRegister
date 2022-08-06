package com.example.cashregister.connection.notused;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/**
 *Hikari connection pool is not used it is working
 * */

public class HikariConPool {
    public static HikariDataSource dataSource = null;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/cash_register?serverTimezone=UTC&allowPublicKeyRetrieval=true");
        config.setUsername("root");
        config.setPassword("Qwerty12345");
        config.addDataSourceProperty("minimumIdle", "5");
        config.addDataSourceProperty("maximumPoolSize", "25");
        dataSource = new HikariDataSource(config);
    }
}
