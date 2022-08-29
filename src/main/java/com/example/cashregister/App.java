package com.example.cashregister;


import com.example.cashregister.Service.Impl.UserServiceImpl;
import com.example.cashregister.controller.MainServlet;
import com.example.cashregister.dao.impl.ReceiptDaoImpl;
import com.example.cashregister.dao.impl.UserDaoImpl;
import com.example.cashregister.security.ValidateUser;

import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.util.Arrays;

import static com.example.cashregister.security.PasswordEncryptionService.*;

public class App {
    public static void main(String[] args) throws SQLException {
      //  System.out.println(new ValidateUser().validateUser("Tom Cat","Qqwe123"));
    }
}

