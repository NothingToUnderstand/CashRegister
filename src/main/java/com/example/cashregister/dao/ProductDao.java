package com.example.cashregister.dao;

import com.example.cashregister.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDao extends MainDao<Product>{
    int createProduct(String name, int quantity, double weight, double price, byte[] img) throws SQLException;

    boolean updateProduct(int id,int quantity, double weight, double price) throws SQLException;

    boolean deleteProduct(int id) throws SQLException;
    Product searchProduct(String name) throws SQLException;

}
