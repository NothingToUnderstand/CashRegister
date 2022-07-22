package com.example.cashregister.dao;


import com.example.cashregister.entity.Report;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
 * DAO layer for program interaction with the report table in the database
 */
public class ReportDAO {
    private static final Logger log = Logger.getLogger(ReportDAO.class);
    /**
     * static block for getting properties from app.properties
     */
    static Properties prop = new Properties();

    static {
        try {
            prop.load(UserDAO.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException ex) {
            log.error("Error in CheckDAO reading property file", ex);
            ex.printStackTrace();
        }
    }

    /**
     * method for adding a new report to the table report
     *
     * @param cashierName name of product
     * @param cashierId   quantity of product
     * @return int report id
     */
    public static int createReport(int cashierId, String cashierName,boolean zReport) {
        log.info("Create report by cashier : " + cashierName + " with id: " + cashierId);
        int id = 0;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("report_create"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cashierId);
            ps.setString(2, cashierName);
            ps.setBoolean(3, zReport);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error during the report creation", e);
            e.printStackTrace();
        }
        if (id == 0) {
            log.warn("Report  not created");
        } else {
            log.info("Report created");
            addReceiptsToReport(id);
            if(zReport){
              ReceiptDAO.deleteAllReceipts();
            }
        }
        return id;
    }

    /**
     * method for adding receipt to report
     *
     * @param reportID report's id
     * @return boolean status
     */
    private static boolean addReceiptsToReport(int reportID) {
        List<Integer> list = new ArrayList<>();
        int a = 0;
        boolean status = false;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("add_receipts_to_report"))) {
            con.setAutoCommit(false);
            list = ReceiptDAO.getReceiptToReport();
            for (Integer i : list) {
                ps.setInt(1, reportID);
                ps.setInt(2, i);
                ps.executeUpdate();
                a++;
            }
            con.commit();
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error during adding receipt to report ", e);
            e.printStackTrace();
        }
        status = a == list.size();
        if (status) {
            log.info("Adding receipts to report successfully");
            addPriceAndAmountToReport(reportID);
        } else {
            log.warn("Adding receipts to report is failed");
        }
        return status;
    }


    public static Report getReport(int id) {
        log.info("Get report with id: " + id);
        Report report = new Report();
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("get_report"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                report = new Report(rs.getInt("id"),
                        rs.getInt("cashier_id"),
                        rs.getString("cashier_name"),
                        rs.getInt("number_of_checks"),
                        rs.getDouble("total_sum"),
                        rs.getString("date_time"),
                        rs.getBoolean("z_report")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error during getting the report", e);
            e.printStackTrace();
        }
        if (report.getId() != 0) {
            log.info("Report found");
        } else {
            log.warn("Report not found");
        }
        return report;
    }

    /**
     * private method for adding general price and amount of products to receipt
     *
     * @param id report's id
     * @return boolean status
     */
    private static boolean addPriceAndAmountToReport(int id) {
        log.info("Adding price and amount to report with id:" + id);
        boolean status = false;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("price_and_amount_to_report"))) {
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error during adding the price and amount to report", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("Adding price and amount to report is successfully");
        } else {
            log.warn("Adding  price and amount to report is failed");
        }
        return status;
    }
}
