package com.example.cashregister.dao.impl;


import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.entity.Product;
import com.example.cashregister.entity.Receipt;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.cashregister.connection.ApacheConPool.getConnection;
import static com.example.cashregister.Service.extra.Properties.getProperty;


/*
 * DAO layer for program interaction with the receipt table in the database
 */
@RequestScoped
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
    public int createReceipt(int cashierId, String cashierName) throws SQLException {
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
            throw e;
        }
        return id;
    }

    /**
     * method for adding product to receipt
     *
     * @param receiptId receipt id
     * @param amount    product's amount to add in check
     * @param productId product's id
     * @return boolean status
     */
    @Override
    public boolean addProductToReceipt(int receiptId, int productId, int amount) throws SQLException {
        log.info("Add product with id:" + productId + " to  check with id: " + receiptId);
        boolean status = ifReceiptIsClosed(receiptId);
        if (status) {
            log.warn("Can not add products to receipt because the receipt with id: " + receiptId + "is closed");
            return false;
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(getProperty("add_product_to_receipt"));
            ps.setInt(1, receiptId);
            ps.setInt(2, productId);
            ps.setInt(3, amount);
            ps.setInt(4, amount);
            ps.setInt(5, productId);
            status = ps.executeUpdate() == 1;
            addPriceAndAmountToReceipt(con, receiptId);
            new ProductDaoImpl().decreaseAmount(con, productId, amount);
            con.commit();
        } catch (SQLException e) {
            log.error("Error during adding product to receipt ", e);
            e.printStackTrace();
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
        return status;
    }

    /**
     * private method for adding general price and amount of products to check
     *
     * @param receiptId check's id
     */
    private void addPriceAndAmountToReceipt(Connection con, int receiptId) throws SQLException {
        log.info("Adding price and amount to check with id:" + receiptId);
        try (PreparedStatement ps = con.prepareStatement(getProperty("price_and_amount_to_receipt"))) {
            ps.setInt(1, receiptId);
            ps.setInt(2, receiptId);
            ps.setInt(3, receiptId);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error during the price and amount adding to receipt", e);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * method for removing receipt
     *
     * @param id receipt id
     * @return boolean status
     */
    @Override
    public boolean deleteReceipt(int id) throws SQLException {
        log.info("Delete receipt with id: " + id);
        boolean status;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("delete_receipt"))) {
            ps.setInt(1, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error("Error during deleting receipt", e);
            e.printStackTrace();
            throw e;
        }
        return status;
    }

    /**
     * method for removing product in check
     *
     * @param idReceipt     receipt id
     * @param numberProduct product number
     * @return boolean status
     */
    @Override
    public boolean deleteProductInReceipt(int idReceipt, int productId, int numberProduct) throws SQLException {
        log.info("Delete product with number: " + numberProduct + " in check with id: " + idReceipt);
        boolean status = ifReceiptIsClosed(idReceipt);
        if (status) {
            log.warn("Can not delete products in check because the check with id: " + idReceipt + "is closed");
            return false;
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(getProperty("delete_product_in_receipt"));
            ps.setInt(1, numberProduct);
            status = ps.executeUpdate() == 1;
            addPriceAndAmountToReceipt(con, idReceipt);
            new ProductDaoImpl().increaseAmount(con, productId, numberProduct);
            con.commit();
        } catch (SQLException e) {
            log.error("Error during deleting product in receipt ", e);
            e.printStackTrace();
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
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
    public Receipt get(int id) throws SQLException {
        log.info("Get  receipt with id: " + id);
        Receipt receipt = null;
        List<Product> products = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_products_for_receipt"));
             PreparedStatement psc = con.prepareStatement(getProperty("get_receipt"))) {
            psc.setInt(1, id);
            ps.setInt(1, id);
            ResultSet rsc = psc.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("number"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("amount_of_product"),
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
        } catch (SQLException e) {
            log.error("Error during getting all receipts");
            e.printStackTrace();
            throw e;
        }
        return receipt;
    }

    /**
     * method gets the all receipts
     *
     * @return List<Receipt> checks
     */
    @Override
    public ArrayList<Receipt> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException {
        String query = String.format(getProperty("get_all_receipts"), column + " " + direction);
        ArrayList<Receipt> receipts = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, limitfrom);
            ps.setInt(2, limitquantity);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                receipts.add(get(rs.getInt("id")));
            }
        } catch (SQLException e) {
            log.error("Error during getting all receipts", e);
            e.printStackTrace();
            throw e;
        }
        return receipts;
    }


    /**
     * method to close the receipt
     *
     * @param id what receipt to close
     * @return boolean status
     */
    @Override
    public boolean closeReceipt(int id) throws SQLException {
        boolean status;
        status = ifReceiptIsClosed(id);
        if (status) {
            log.warn("Can not close the receipt with id: " + id + " twice");
            return false;
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(getProperty("close_receipt"));
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, id);
            status = ps.executeUpdate() == 1;
            addReceiptAndProductsToArchive(con, id);
            con.commit();
        } catch (SQLException e) {
            log.error("Error during closing the receipt", e);
            e.printStackTrace();
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
        return status;
    }



    /**
     * private method to check if the receipt is closed
     *
     * @param id receipt id
     * @return List<Integer> receipt
     */
    private boolean ifReceiptIsClosed(int id) throws SQLException {
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("if_receipt_closed"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getTimestamp(1) != null;
            }
        } catch (SQLException e) {
            log.error("Error during getting if the receipt is closed", e);
            e.printStackTrace();
            throw e;
        }
        return status;
    }

    /**
     * private method to add receipt to archive
     *
     * @param id receipt id
     */
    private void addReceiptAndProductsToArchive(Connection con, int id) throws SQLException {
        log.info("Adding receipt to archive with id: " + id);
        try (PreparedStatement ps = con.prepareStatement(getProperty("add_to_archive_receipt"));
             PreparedStatement psc = con.prepareStatement(getProperty("add_product_to_archive_receipt"))) {
            ps.setInt(1, id);
            psc.setInt(1, id);
            ps.executeUpdate();
            psc.executeUpdate();
        } catch (SQLException e) {
            log.error("Error during adding products to receipt", e);
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * method for removing all receipts
     */




    @Override
    public int countRows() throws SQLException {
        int amount = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("count_rows_in_receipts"))) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Error during getting amount of reports", e);
            e.printStackTrace();
            throw e;
        }
        return amount;
    }

    @Override
    public boolean cancelProduct(int number, int amount, int idReceipt, int idProduct) throws SQLException {
        log.info("Cancel product with number: " + number + " in amount: " + amount);
        boolean status = ifReceiptIsClosed(idReceipt);
        if (status) {
            log.warn("Can not delete products in check because the check with id: " + idReceipt + "is closed");
            return false;
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(String.format(getProperty("change_product_amount_in_receipt"), amount));
            ps.setInt(1, idProduct);
            ps.setInt(2, number);
            status = ps.executeUpdate() == 1;
            addPriceAndAmountToReceipt(con,idReceipt);
            new ProductDaoImpl().increaseAmount(con,idProduct, amount);
            con.commit();
        } catch (SQLException e) {
            log.error("Error during changing product in receipt ", e);
            e.printStackTrace();
            if (con != null) {
                con.rollback();
            }
            throw e;
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
        return status;
    }
}
