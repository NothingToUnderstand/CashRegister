package com.example.cashregister.entity;
/**
 * Entity of report
 * */
public class Report {
    private int id;
    private int cashier_id;
    private String cashier_name;
    private int number_of_receipts;
    private double total_sum;
    private String date;
    private boolean z_report;

    public Report() {
        this.id = 0;
    }

    public Report(int id, int cashier_id, String cashier_name, int number_of_receipts, double total_sum, String date,boolean z) {
        this.id = id;
        this.cashier_id = cashier_id;
        this.cashier_name = cashier_name;
        this.number_of_receipts = number_of_receipts;
        this.total_sum = total_sum;
        this.date = date;
        this.z_report=z;
    }


    public int getId() {
        return id;
    }

    public int getCashier_id() {
        return cashier_id;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public int getNumberOfReceipts() {
        return number_of_receipts;
    }

    public double getTotalSum() {
        return total_sum;
    }

    public String getDate() {
        return date;
    }

    public boolean isZReport() {
        return z_report;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", cashier_id=" + cashier_id +
                ", cashier_name='" + cashier_name + '\'' +
                ", number_of_products=" + number_of_receipts +
                ", total_sum=" + total_sum +
                ", date='" + date + '\'' +
                ", z_report=" + z_report +
                '}';
    }
}
