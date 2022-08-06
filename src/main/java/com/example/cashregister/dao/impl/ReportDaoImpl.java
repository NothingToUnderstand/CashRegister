package com.example.cashregister.dao.impl;


import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.dao.ReportDao;
import com.example.cashregister.entity.Report;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static com.example.cashregister.connection.ApacheConPool.getConnection;
import static com.example.cashregister.property.Properties.getProperty;

/*
 * DAO layer for program interaction with the report table in the database
 */
public class ReportDaoImpl implements ReportDao {
    private static final Logger log = Logger.getLogger(ReportDaoImpl.class);


    /**
     * method for adding a new report to the table report
     *
     * @param cashierName name of product
     * @param cashierId   quantity of product
     * @return int report id
     */
    @Override
    public int createReport(int cashierId, String cashierName, boolean zReport) {
        log.info("Create report by cashier : " + cashierName + " with id: " + cashierId);
        ReceiptDaoImpl receiptDao=new ReceiptDaoImpl();
        int id = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("report_create"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cashierId);
            ps.setString(2, cashierName);
            ps.setBoolean(3, zReport);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Error during the report creation", e);
            e.printStackTrace();
        }
        if (id == 0) {
            log.warn("Report  not created");
        } else {
            log.info("Report created");
            addReceiptsToReport(id);
            if (zReport) {
                receiptDao.deleteAllReceipts();
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

    private boolean addReceiptsToReport(int reportID) {
        ReceiptDao receiptDao=new ReceiptDaoImpl();
        List<Integer> list = new ArrayList<>();
        int a = 0;
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("add_receipts_to_report"))) {
            con.setAutoCommit(false);
            list = receiptDao.getReceiptToReport();
            for (Integer i : list) {
                ps.setInt(1, reportID);
                ps.setInt(2, i);
                ps.executeUpdate();
                a++;
            }
            con.commit();
        } catch (SQLException e) {
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
    /**
     *  method to get a report
     *
     * @param id report's id
     * @return report
     */
    @Override
    public Report getReport(int id) {
        log.info("Get report with id: " + id);
        Report report = new Report();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_report"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                report = new Report(rs.getInt("id"),
                        rs.getInt("cashier_id"),
                        rs.getString("cashier_name"),
                        rs.getInt("number_of_receipts"),
                        rs.getDouble("total_sum"),
                        rs.getString("date_time"),
                        rs.getBoolean("z_report")
                );
            }
        } catch (SQLException e) {
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

    @Override
    public List<Report> getAllReports(String column, String direction, Integer limitfrom, Integer limitquantity) {
        String query = String.format(getProperty("get_all_reports"), column + " " + direction);
        ArrayList<Report> reports = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, limitfrom);
            ps.setInt(2, limitquantity);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("id"),
                        rs.getInt("cashier_id"),
                        rs.getString("cashier_name"),
                        rs.getInt("number_of_receipts"),
                        rs.getDouble("total_sum"),
                        rs.getString("date_time"),
                        rs.getBoolean("z_report"));
                reports.add(report);
            }
        } catch (SQLException e) {
            log.error("Error during getting all reports", e);
            e.printStackTrace();

        }
        return reports;
    }

    @Override
    public int countRows() {
        int amount = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("count_rows_in_reports"))) {
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
     * private method for adding general price and amount of products to receipt
     *
     * @param id report's id
     * @return boolean status
     */
    private boolean addPriceAndAmountToReport(int id) {
        log.info("Adding price and amount to report with id:" + id);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("price_and_amount_to_report"))) {
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException  e) {
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
