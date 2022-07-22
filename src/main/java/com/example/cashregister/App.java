package com.example.cashregister;

import com.example.cashregister.dao.UserDAO;

public class App {
    public static void main(String[] args) {
        System.out.println(UserDAO.getUser(16));
    }
}
