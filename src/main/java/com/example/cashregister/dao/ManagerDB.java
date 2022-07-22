package com.example.cashregister.dao;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * the class that implements the database connection with the "Singleton" design pattern
 */
public class ManagerDB {
    private static final Logger log = Logger.getLogger(ManagerDB.class);

    private static ManagerDB instance;

    // Singleton
    private ManagerDB() {
    }

    synchronized public static ManagerDB getInstance() {
        if (instance == null) {
            instance = new ManagerDB();
        }
        return instance;
    }

    static Properties prop = new Properties();

    static {
        try {
            prop.load(UserDAO.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException ex) {
            log.error("Error in UserDao reading property file", ex);
            ex.printStackTrace();
        }
    }

    public Connection getConnection() throws ClassNotFoundException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch ( SQLException e) {
            log.error("Connection Fail", e);
            e.printStackTrace();
        }
        if (con != null) {
            log.info("Connection success");
            return con;
        } else {
            ClassNotFoundException e = new ClassNotFoundException();
            log.error("Connection Fail", e);
            throw e;
        }

    }
}
