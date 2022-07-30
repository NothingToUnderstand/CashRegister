package com.example.cashregister.dao.impl;

import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.entity.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.cashregister.connection.ApacheConPool.getConnection;
import static com.example.cashregister.property.Properties.getProperty;

/**
 * DAO layer for program interaction with the product table in the database
 **/
public class ProductDaoImpl implements ProductDao {

    private static final Logger log = Logger.getLogger(ProductDaoImpl.class);

    /**
     * method for adding a new item (product) to the products table
     *
     * @param name     name of product
     * @param quantity quantity of product
     * @param weight   weight of this product
     * @param price    price of this product
     * @param img      img of this product
     * @return int product id
     */
    @Override
    public int createProduct(String name, int quantity, double weight, double price, byte[] img) {
        log.info("Add product to DB: " + name + " " + quantity + " " + weight + " " + price);
        int id = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("create_product"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setInt(2, quantity);
            ps.setDouble(3, weight);
            ps.setDouble(4, price);
            ps.setBytes(5, img);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Error during the product creation", e);
            e.printStackTrace();
        }
        if (id == 0) {
            log.warn("Product  not created");
        } else {
            log.info("Product created with id: " + id);
        }
        return id;
    }


    /**
     * method for changing the product field by its id
     *
     * @param id       product's id
     * @param name     name of product
     * @param quantity quantity of product
     * @param weight   weight of this product
     * @param price    price of this product
     * @param img      product image
     * @return boolean status
     */
    @Override
    public boolean updateProduct(int id, String name, int quantity, double weight, double price, byte[] img) {
        log.info("Updating product with id: " + id);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("update_product"))) {
            ps.setString(1, name);
            ps.setInt(2, quantity);
            ps.setDouble(3, weight);
            ps.setDouble(4, price);
            ps.setBytes(5, img);
            ps.setInt(6, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error("Error during updating the product", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("Product update is successfull");
        } else {
            log.warn("Product update failed");
        }
        return status;
    }


    /**
     * product delete method
     *
     * @param id product's id
     * @return boolean status
     */
    @Override
    public boolean deleteProduct(int id) {
        log.info("Delete product with id: " + id);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("delete_product"))) {
            ps.setInt(1, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error("Error during removing the product");
            e.printStackTrace();
        }
        if (status) {
            log.info("Product deleted successfully");
        } else {
            log.warn("Product delete failed");
        }
        return status;
    }


    /**
     * the method receives the data of all products
     *
     * @param column        name of the column in bd
     * @param direction     asc or desc
     * @param limitfrom     from what row to show
     * @param limitquantity how many rows to show
     * @return List<User> List of all users
     */
    @Override
    public ArrayList<Product> getAllProducts(String column, String direction, Integer limitfrom, Integer limitquantity) {
        String query = String.format(getProperty("get_all_products"), column + " " + direction);
        ArrayList<Product> products = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, limitfrom);
            ps.setInt(2, limitquantity);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("weight"),
                        rs.getDouble("price"),
                        rs.getBytes("img")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            log.error("Error during getting all products", e);
            e.printStackTrace();

        }
        return products;
    }

    /**
     * the method receives information about product
     *
     * @param id product's id
     * @return product
     */
    @Override
    public Product getProduct(int id) {
        log.info("Get product with id: " + id);
        Product product = new Product();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_product"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("weight"),
                        rs.getDouble("price"),
                        rs.getBytes("img"));
            }
        } catch (SQLException e) {
            log.error("Error during getting product", e);
            e.printStackTrace();
        }
        if (product.getId() != 0) {
            log.info("Product found");
        } else {
            log.warn("Product not found");
        }
        return product;
    }


    /**
     * the method receives information about product
     *
     * @param name product's name
     * @return product
     */
    @Override
    public Product searchProduct(String name) {
        log.info("Get product with name: " + name);
        Product product = new Product();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_product_by_name"))) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("weight"),
                        rs.getDouble("price"),
                        rs.getBytes("img"));
            }
        } catch (SQLException e) {
            log.error("Error during getting product", e);
            e.printStackTrace();
        }
        if (product.getId() != 0) {
            log.info("Product found");
        } else {
            log.warn("Product not found");
        }
        return product;
    }

    /**
     * method to get the amount of a product by its id
     *
     * @return int amount
     */
    @Override
    public int countRows() {
        int amount = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("count_rows_in_products"))) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Error during getting amount of products", e);
            e.printStackTrace();
        }
        return amount;
    }


    /**
     * method to get the amount of a product by its id
     *
     * @param id :the id of a product
     * @return int amount
     */
    @Override
    public int getAmount(int id) {
        int amount = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_product_amount"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);
            }
        } catch (SQLException  e) {
            log.error("Error during getting product", e);
            e.printStackTrace();
        }
        if (amount <= 0) {
            log.warn("There is not enough amount of the product with id: " + id);
        } else {
            log.info("The amount of product with id:" + id + " at db: " + amount);
        }
        return amount;
    }

    /**
     * method to set a new amount of a product by its id
     *
     * @param id     :the id of a product
     * @param amount :the new amount of a product at db
     * @return boolean status
     */
    @Override
    public boolean setAmount(int id, int amount) {
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("set_product_amount"))) {
            ps.setInt(1, amount);
            ps.setInt(2, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error("Error during getting amount of product", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("Product amount updated");
        } else {
            log.warn("Product setting new amount failed");
        }
        return status;
    }
}

