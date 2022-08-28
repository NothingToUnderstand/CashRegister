package com.example.cashregister.dao.impl;


import com.example.cashregister.dao.ReportDao;
import com.example.cashregister.entity.Report;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static com.example.cashregister.connection.ApacheConPool.getConnection;
import static com.example.cashregister.Service.extra.Properties.getProperty;

/*
 * DAO layer for program interaction with the report table in the database
 */@RequestScoped
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
    public int createReport(int cashierId, String cashierName, boolean zReport) throws SQLException {
        log.info("Create report by cashier : " + cashierName + " with id: " + cashierId);
        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try  {
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(getProperty("report_create"), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cashierId);
            ps.setString(2, cashierName);
            ps.setBoolean(3, zReport);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            addReceiptsToReport(con,id);
            if (zReport) {
                deleteAllReceipts(con);
            }
            con.commit();
        } catch (SQLException e) {
            log.error("Error during the report creation", e);
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
        return id;
    }
    private void deleteAllReceipts(Connection con) throws SQLException {
        log.info("Delete all receipts");
        try (PreparedStatement ps = con.prepareStatement(getProperty("delete_all_receipts"))) {
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error during deleting all receipts", e);
            e.printStackTrace();
            throw e;
        }

    }
    /**
     * method for adding receipt to report
     *
     * @param reportID report's id
     * @return boolean status
     */

    private void addReceiptsToReport(Connection con,int reportID) throws SQLException {
        List<Integer> list;
        try (PreparedStatement ps = con.prepareStatement(getProperty("add_receipts_to_report"))) {
            list = getReceiptToReport();
            for (Integer i : list) {
                ps.setInt(1, reportID);
                ps.setInt(2, i);
                ps.executeUpdate();
            }
            addPriceAndAmountToReport(con,reportID);
        } catch (SQLException e) {
            log.error("Error during adding receipt to report ", e);
            e.printStackTrace();
            throw e;
        }



    }
    /**
     *  method to get a report
     *
     * @param id report's id
     * @return report
     */
    @Override
    public Report get(int id) throws SQLException {
        log.info("Get report with id: " + id);
        Report report = null;
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
            throw e;
        }
        return report;
    }

    @Override
    public ArrayList<Report> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException {
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
            throw e;
        }
        return reports;
    }

    @Override
    public int countRows() throws SQLException {
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
            throw e;
        }
        return amount;
    }

    /**
     * private method for adding general price and amount of products to receipt
     *
     * @param id report's id
     * @return boolean status
     */
    private void addPriceAndAmountToReport(Connection con,int id) throws SQLException {
        log.info("Adding price and amount to report with id:" + id);
        try (PreparedStatement ps = con.prepareStatement(getProperty("price_and_amount_to_report"))) {
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
           ps.executeUpdate();
        } catch (SQLException  e) {
            log.error("Error during adding the price and amount to report", e);
            e.printStackTrace();
            throw e;
        }

    }
    /**
     * method gets  all closed receipt id less than 12h
     *
     * @return List<Integer> receipt
     */

    private List<Integer> getReceiptToReport() throws SQLException {
        List<Integer> receipt = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_receipts_to_report"))) {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis() - 43200000));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                receipt.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            log.error("Error during getting closed checks id", e);
            e.printStackTrace();
            throw e;
        }
        return receipt;
    }

}
