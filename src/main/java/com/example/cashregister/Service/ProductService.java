package com.example.cashregister.Service;

import com.example.cashregister.entity.Product;

import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;

public interface ProductService extends MainService<Product>{
    int createProduct(String name, String quantity, String weight, String price, byte[] img) throws SQLException;

    boolean updateProduct(String id,String quantity, String weight, String price) throws SQLException;

    boolean deleteProduct(String id)throws SQLException;
    Product searchProduct(String name) throws SQLException;

}
