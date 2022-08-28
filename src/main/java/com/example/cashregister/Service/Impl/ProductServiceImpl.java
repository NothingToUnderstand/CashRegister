package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.ProductService;
import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.entity.Product;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    @Inject
    public ProductServiceImpl(ProductDao productDao){
        this.productDao=productDao;
    }

    private static final Logger log = Logger.getLogger(ProductServiceImpl.class);
    @Override
    public ArrayList<Product> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException {
        return this.productDao.getAll(column,direction,limitfrom,limitquantity);
    }

    @Override
    public int countRows() throws SQLException {
        return this.productDao.countRows();
    }

    @Override
    public Product get(String idProduct) throws SQLException {
        int id = 0;
        try {
            id = Integer.parseInt(idProduct);
        } catch (NumberFormatException e) {
            log.warn("error id");
           return null;
        }
        return this.productDao.get(id);
    }

    @Override
    public int createProduct(String name, String quantity_, String weight_, String price_, Part part) throws SQLException,NumberFormatException{
        if (name.isBlank() || quantity_.isBlank() || weight_.isBlank() ||
                price_.isBlank() || part==null) {
            throw new NumberFormatException();
        }
        byte[]img;
        int quantity;
        double weight;
        double price;
        try {
            quantity=Integer.parseInt(quantity_);
            weight=Double.parseDouble(weight_);
            price=Double.parseDouble(price_);
            img = part.getInputStream().readAllBytes();
        } catch (IOException|NumberFormatException e) {
            e.printStackTrace();
            log.error("number format exception", e);
            throw new NumberFormatException();
        }
        return this.productDao.createProduct(name,quantity,weight,price,img);
    }

    @Override
    public boolean updateProduct(String id_,String name, String quantity_, String weight_, String price_, Part part) throws SQLException,NumberFormatException{
        if (id_==null || name==null || quantity_==null || weight_==null ||
                price_==null|| part==null) {
            throw new NumberFormatException();
        }
        int id;
        byte[]img;
        int quantity;
        double weight;
        double price;
        try {
            id=Integer.parseInt(id_);
            quantity=Integer.parseInt(quantity_);
            weight=Double.parseDouble(weight_);
            price=Double.parseDouble(price_);
            img = part.getInputStream().readAllBytes();
        } catch (IOException|NumberFormatException e) {
            e.printStackTrace();
            log.error("number format exception", e);
            throw new NumberFormatException();
        }
        return this.productDao.updateProduct(id, name, quantity, weight, price, img);
    }

    @Override
    public boolean deleteProduct(String idProduct) throws SQLException {
        int id = 0;
        try {
            id = Integer.parseInt(idProduct);
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            throw e;
        }
        return this.productDao.deleteProduct(id);
    }

    @Override
    public Product searchProduct(String name) throws SQLException {
        if(name.isBlank()){
            return null;
        }
        return this.productDao.searchProduct(name);
    }
}
