package com.example.cashregister.dao.impl;

import com.example.cashregister.dao.ArchiveReceiptDao;

import com.example.cashregister.entity.Product;
import com.example.cashregister.entity.Receipt;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.cashregister.connection.ApacheConPool.getConnection;
import static com.example.cashregister.property.Properties.getProperty;


public class ArchiveReceiptDaoImpl implements ArchiveReceiptDao {
    private static final Logger log = Logger.getLogger(ArchiveReceiptDaoImpl.class);
    /**
     * method gets the receipt from archive
     * @param id receipt id
     * @return receipt
     */
    @Override
    public Receipt get(int id) {
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
        }
        if (receipt.getId() != 0) {
            log.info("Receipt found");
        } else {
            log.warn("Receipt not found");
        }
        return receipt;
    }
    @Override
    public int countRows() {
        int amount = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("count_rows_in_archive_receipts"))) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Error during getting amount of reports", e);
            e.printStackTrace();
        }
        return amount;
    }
    /**
     * method gets the all receipts from archive
     * @return List<Receipt> checks
     */
    @Override
    public  ArrayList<Receipt> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) {
        String query = String.format(getProperty("get_all_receipts_from_archive"), column + " " + direction);
        ArrayList<Receipt> receipts = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, limitfrom);
            ps.setInt(2, limitquantity);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                receipts.add(get(rs.getInt("id")));
            }
        } catch (SQLException  e) {
            log.error("Error during getting all receipts from archive", e);
            e.printStackTrace();
        }
        return receipts;
    }

}
