package com.example.cashregister.dao;

import com.example.cashregister.entity.Product;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * DAO layer for program interaction with the product table in the database
 **/
public class ProductDAO {

    private static final Logger log = Logger.getLogger(ProductDAO.class);
    /**
     * static block for getting properties from app.properties
     */
    static Properties prop = new Properties();

    static {
        try {
            prop.load(UserDAO.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException ex) {
            log.error("Error in ProductDAO reading property file", ex);
            ex.printStackTrace();
        }
    }


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
    public static int createProduct(String name, int quantity, double weight, double price, byte[] img) {
        log.info("Add product to DB: " + name + " " + quantity + " " + weight + " " + price);
        int id = 0;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("create_product"), PreparedStatement.RETURN_GENERATED_KEYS)) {
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
        } catch (SQLException | ClassNotFoundException e) {
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
    public static boolean updateProduct(int id, String name, int quantity, double weight, double price, byte[] img) {
        log.info("Updating product with id: " + id);
        boolean status = false;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("update_product"))) {
            ps.setString(1, name);
            ps.setInt(2, quantity);
            ps.setDouble(3, weight);
            ps.setDouble(4, price);
            ps.setBytes(5, img);
            ps.setInt(6, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
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
    public static boolean deleteProduct(int id) {
        log.info("Delete product with id: " + id);
        boolean status = false;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("delete_product"))) {
            ps.setInt(1, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
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
     * @return List<User> List of all users
     */
    public static List<Product> getAllProducts() {
        log.info("Get all products");
        List<Product> products = new ArrayList<>();
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("get_all_products"))) {
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
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error during getting all products", e);
            e.printStackTrace();
        }
        log.info("List with products: " + products);
        return products;
    }

    /**
     * the method receives information about product
     *
     * @param id product's id
     * @return product
     */
    public static Product getProduct(int id) {
        log.info("Get product with id: " + id);
        Product product = new Product();
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("get_product"))) {
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
        } catch (SQLException | ClassNotFoundException e) {
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
     * @param id :the id of a product
     * @return int amount
     */

    protected static int getAmount(int id) {
        int amount = 0;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("get_product_amount"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
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
    protected static boolean setAmount(int id, int amount) {
        boolean status = false;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("set_product_amount"))) {
            ps.setInt(1, amount);
            ps.setInt(2, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
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

