package com.example.cashregister.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MainService<T>{
    ArrayList<T> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException;
    int countRows() throws SQLException;
    T get(String id) throws SQLException;
}
