package com.example.cashregister.dao;

import com.example.cashregister.entity.Product;

import java.util.ArrayList;

public interface ProductDao {
    int createProduct(String name, int quantity, double weight, double price, byte[] img);

    boolean updateProduct(int id, String name, int quantity, double weight, double price, byte[] img);

    boolean deleteProduct(int id);

    ArrayList<Product> getAllProducts(String column, String direction, Integer limitfrom, Integer limitquantity);

    Product getProduct(int id);

    Product searchProduct(String name);

    int countRows();


}
