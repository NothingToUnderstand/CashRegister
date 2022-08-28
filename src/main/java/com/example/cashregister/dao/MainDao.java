package com.example.cashregister.dao;


import java.sql.SQLException;
import java.util.ArrayList;

public interface MainDao<T> {
    ArrayList<T> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException;
    int countRows() throws SQLException;
    T get(int id) throws SQLException;
}
