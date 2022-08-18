package com.example.cashregister.dao;


import java.util.ArrayList;

public interface MainDao<T> {
    ArrayList<T> getAll(String column, String direction, Integer limitfrom, Integer limitquantity);
    int countRows();
    T get(int id);
}
