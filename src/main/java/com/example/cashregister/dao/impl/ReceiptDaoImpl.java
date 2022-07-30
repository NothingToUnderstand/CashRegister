package com.example.cashregister.dao.impl;


import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.entity.Product;
import com.example.cashregister.entity.Receipt;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.cashregister.connection.ApacheConPool.getConnection;
import static com.example.cashregister.property.Properties.getProperty;


/*
 * DAO layer for program interaction with the receipt table in the database
 */

public class ReceiptDaoImpl implements ReceiptDao {
    private static final Logger log = Logger.getLogger(ReceiptDaoImpl.class);

    /**
     * method for adding a new receipt to the  table checks
     *
     * @param cashierName name of product
     * @param cashierId   quantity of product
     * @return int receipt id
     */
    @Override
    public  int createReceipt(int cashierId, String cashierName) {
        log.info("Create receipt by cashier : " + cashierName + " with id: " + cashierId);
        int id = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("receipt_create"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cashierId);
            ps.setString(2, cashierName);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Error during the receipt creation", e);
            e.printStackTrace();
        }
        if (id == 0) {
            log.warn("Receipt  not created");
        } else {
            log.info("Receipt created");
        }
        return id;
    }

    /**
     * method for adding product to receipt
     *
     * @param receiptId  receipt id
     * @param amount    product's amount to add in check
     * @param productId product's id
     * @return boolean status
     */
    @Override
    public  boolean addProductToReceipt(int receiptId, int productId, int amount) {
        log.info("Add product with id:" + productId + " to  check with id: " + receiptId);
        ProductDao productDao=new ProductDaoImpl();
        boolean status = ifReceiptIsClosed(receiptId);
        if(status){
            log.warn("Can not add products to receipt because the receipt with id: "+receiptId+"is closed");
            return false;
        }
        int amountAtDB = productDao.getAmount(productId);
        boolean checkAmount = amountAtDB >= amount;
        if (!checkAmount) {
            log.warn("Not enough amount of a product with id: " + productId + "to add to the receipt with id: " + receiptId);
            return status;
        }
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("add_product_to_receipt"))) {
            ps.setInt(1, receiptId);
            ps.setInt(2, productId);
            ps.setInt(3, amount);
            ps.setInt(4, amount);
            ps.setInt(5, productId);
            status = ps.executeUpdate() == 1;
        } catch (SQLException  e) {
            log.error("Error during adding product to receipt ", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("Adding product to check is successfully");
            addPriceAndAmountToReceipt(receiptId);
           productDao.setAmount(productId, (amountAtDB - amount));
        } else {
            log.warn("Adding product to receipt is failed");
        }
        return status;
    }

    /**
     * private method for adding general price and amount of products to check
     *
     * @param receiptId check's id
     * @return boolean status
     */
    private  boolean addPriceAndAmountToReceipt(int receiptId) {
        log.info("Adding price and amount to check with id:" + receiptId);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("price_and_amount_to_receipt"))) {
            ps.setInt(1, receiptId);
            ps.setInt(2, receiptId);
            ps.setInt(3, receiptId);
            status = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error("Error during the price and amount adding to receipt", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("Adding price and amount to receipt is successfully");
        } else {
            log.warn("Adding  price and amount to receipt is failed");
        }
        return status;
    }

    /**
     * method for removing receipt
     *
     * @param id receipt id
     * @return boolean status
     */
    @Override
    public  boolean deleteReceipt(int id) {
        log.info("Delete receipt with id: " + id);
        boolean status = false;
        try (Connection con =getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("delete_receipt"))) {
            ps.setInt(1, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException  e) {
            log.error("Error during deleting receipt", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("Receipt deleted successfully");
        } else {
            log.warn("Receipt delete failed");
        }
        return status;
    }

    /**
     * method for removing product in check
     *
     * @param idReceipt   receipt id
     * @param idProduct product id
     * @return boolean status
     */
    @Override
    public  boolean deleteProductInReceipt(int idReceipt, int idProduct) {
        log.info("Delete product with id: " + idProduct + " in check with id: " + idReceipt);
        boolean status = false;
        status = ifReceiptIsClosed(idReceipt);
        if(status){
            log.warn("Can not delete products in check because the check with id: "+idReceipt+"is closed");
            return false;
        }
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("delete_product_in_receipt"))) {
            ps.setInt(1, idReceipt);
            ps.setInt(2, idProduct);
            status = ps.executeUpdate() == 1;
        } catch (SQLException  e) {
            log.error("Error during deleting product in receipt ", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("Product from check deleted successfully");
            addPriceAndAmountToReceipt(idReceipt);
        } else {
            log.warn("Product from receipt delete failed");
        }
        return status;
    }

    /**
     * method gets the receipt
     *
     * @param id receipt id
     * @return receipt
     */
    @Override
    public  Receipt getReceipt(int id) {
        log.info("Get  receipt with id: "+id);
        Receipt receipt = new Receipt();
        List<Product> products = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_products_for_receipt"));
             PreparedStatement psc = con.prepareStatement(getProperty("get_receipt"))) {
            psc.setInt(1, id);
            ps.setInt(1, id);
            ResultSet rsc = psc.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("weight"),
                        rs.getDouble("price"),
                        rs.getBytes("img"));
                products.add(product);
            }
            if (rsc.next()) {
                receipt = new Receipt(rsc.getInt("id"),
                        rsc.getInt("cashier_id"),
                        rsc.getString("cashier_name"),
                        rsc.getInt("number_of_products"),
                        rsc.getDouble("total_sum"),
                        rsc.getString("open_date_time"),
                        rsc.getString("close_date_time"),
                        products);
            }
        } catch (SQLException  e) {
            log.error("Error during getting all users");
            e.printStackTrace();
        }
        if (receipt.getId() != 0) {
            log.info("Receipt found");
        } else {
            log.warn("Receipt not found");
        }
        return receipt;
    }

    /**
     * method gets the all receipts
     * @return List<Receipt> checks
     */
    @Override
    public  List<Receipt> getALLReceipts() {
        List<Receipt> receipts = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_all_receipts"))) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                receipts.add(getReceipt(rs.getInt("id")));
            }
        } catch (SQLException  e) {
            log.error("Error during getting all receipts", e);
            e.printStackTrace();
        }
        log.info("All receipts: " + receipts);
        return receipts;
    }

    /**
     * method to close the receipt
     *
     * @param id what receipt to close
     * @return boolean status
     */
    @Override
    public  boolean closeReceipt(int id) {
        boolean status = false;
        status = ifReceiptIsClosed(id);
        if(status){
            log.warn("Can not close the receipt with id: "+id+" twice");
            return false;
        }
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("close_receipt"))) {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error("Error during closing the receipt", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("Receipt closed");
            addReceiptAndProductsToArchive(id);
        } else {
            log.warn("Receipt not closed");
        }
        return status;
    }

    /**
     * method gets  all closed receipt id less than 12h
     *
     * @return List<Integer> receipt
     */
    @Override
    public List<Integer> getReceiptToReport() {
        List<Integer> receipt = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_receipts_to_report"))) {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis() - 43200000));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                receipt.add(rs.getInt("id"));
            }
        } catch (SQLException  e) {
            log.error("Error during getting closed checks id", e);
            e.printStackTrace();
        }
        log.info("Closed receipt id: " + receipt);
        return receipt;
    }


    /**
     * private method to check if the receipt is closed
     *
     * @param id receipt id
     * @return List<Integer> receipt
     */
    @Override
    public  boolean ifReceiptIsClosed(int id) {
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("if_receipt_closed"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getTimestamp(1)!=null;
            }
        } catch (SQLException  e) {
            log.error("Error during getting if the receipt is closed", e);
            e.printStackTrace();
        }
        if (status){
            log.info("Receipt with id: " + id + " is closed");
        }else {
            log.warn("Receipt with id: " + id + " is not closed");
        }
        return status;
    }

    /**
     * private method to add receipt to archive
     *
     * @param id receipt id
     * @return boolean status
     */
    private  boolean addReceiptAndProductsToArchive(int id){
        log.info("Adding receipt to archive with id: "+id);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("add_to_archive_receipt"));
             PreparedStatement psc = con.prepareStatement(getProperty("add_product_to_archive_receipt"))) {
            ps.setInt(1, id);
            psc.setInt(1, id);
            status = ps.executeUpdate()!=0 && psc.executeUpdate()!=0;
        } catch (SQLException  e) {
            log.error("Error during adding products to receipt", e);
            e.printStackTrace();
        }
        if (status){
            log.info("Receipt with id: " + id + " added to archive");
        }else {
            log.warn("Receipt with id: " + id + " not added to archive");
        }
        return status;
    }


    /**
     * method for removing all receipts
     * @return boolean status
     */
    @Override
    public boolean deleteAllReceipts() {
        log.info("Delete all receipts");
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("delete_all_receipts"))) {
            status = ps.executeUpdate() >0;
        } catch (SQLException  e) {
            log.error("Error during deleting all receipts", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("All receipts deleted successfully");
        } else {
            log.warn("All receipts delete failed");
        }
        return status;
    }

    /**
     * method gets the receipt from archive
     * @param id receipt id
     * @return receipt
     */
    @Override
    public  Receipt getReceiptFromArchive(int id) {
        log.info("Get  receipt from archive with id: "+id);
        Receipt receipt = new Receipt();
        List<Product> products = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_products_to_receipt_from_archive"));
             PreparedStatement psc = con.prepareStatement(getProperty("get_receipt_from_archive"))) {
            psc.setInt(1, id);
            ps.setInt(1, id);
            ResultSet rsc = psc.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("weight"),
                        rs.getDouble("price"),
                        rs.getBytes("img"));
                products.add(product);
            }
            if (rsc.next()) {
                receipt = new Receipt(rsc.getInt("id"),
                        rsc.getInt("cashier_id"),
                        rsc.getString("cashier_name"),
                        rsc.getInt("number_of_products"),
                        rsc.getDouble("total_sum"),
                        rsc.getString("open_date_time"),
                        rsc.getString("close_date_time"),
                        products);
            }
        } catch (SQLException  e) {
            log.error("Error during getting all users");
            e.printStackTrace();
        }
        if (receipt.getId() != 0) {
            log.info("Receipt found");
        } else {
            log.warn("Receipt not found");
        }
        return receipt;
    }

}
