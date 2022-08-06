package com.example.cashregister.entity;

import java.util.List;
/**
 * Entity of receipt
 * */
public class Receipt {
    private int id;
    private int cachier_id;
    private String cachier_name;
    private int number_of_products;
    private double total_sum;
    private String openDate;
    private String closeDate;
    private List<Product> products_in_receipt;

    public Receipt() {
        this.id = 0;
    }

    public Receipt(int id, int cachier_id, String cachier_name, int number_of_products, double total_sum, String openDate, String closeDate, List<Product> products_in_receipt) {
        this.id = id;
        this.cachier_id = cachier_id;
        this.cachier_name = cachier_name;
        this.number_of_products = number_of_products;
        this.total_sum = total_sum;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.products_in_receipt = products_in_receipt;
    }

    public int getId() {
        return id;
    }

    public int getCachier_id() {
        return cachier_id;
    }

    public String getCachier_name() {
        return cachier_name;
    }

    public int getNumber_of_products() {
        return number_of_products;
    }

    public double getTotal_sum() {
        return total_sum;
    }


    public String getOpenDate() {
        return openDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public List<Product> getProductsInReceipt() {
        return products_in_receipt;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", cachier_id=" + cachier_id +
                ", cachier_name='" + cachier_name + '\'' +
                ", number_of_products=" + number_of_products +
                ", total_sum=" + total_sum +
                ", openDate='" + openDate + '\'' +
                ", closeDate='" + closeDate + '\'' +
                ", products_in_check=" + products_in_receipt +
                '}';
    }
}
