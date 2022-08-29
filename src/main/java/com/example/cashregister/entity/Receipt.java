package com.example.cashregister.entity;

import java.util.List;
/**
 * Entity of receipt
 * */
public class Receipt {
    private int id;
    private int cashier_id;
    private String cashier_name;
    private int number_of_products;
    private double total_sum;
    private String openDate;
    private String closeDate;
    private List<Product> products_in_receipt;


    public Receipt(int id, int cashier_id, String cashier_name, int number_of_products, double total_sum, String openDate, String closeDate, List<Product> products_in_receipt) {
        this.id = id;
        this.cashier_id = cashier_id;
        this.cashier_name = cashier_name;
        this.number_of_products = number_of_products;
        this.total_sum = total_sum;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.products_in_receipt = products_in_receipt;
    }

    public int getId() {
        return id;
    }

    public int getCashierId() {
        return cashier_id;
    }

    public String getCashierName() {
        return cashier_name;
    }

    public int getNumberOfProducts() {
        return number_of_products;
    }

    public double getTotalSum() {
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
                ", cachier_id=" + cashier_id +
                ", cachier_name='" + cashier_name + '\'' +
                ", number_of_products=" + number_of_products +
                ", total_sum=" + total_sum +
                ", openDate='" + openDate + '\'' +
                ", closeDate='" + closeDate + '\'' +
                ", products_in_check=" + products_in_receipt +
                '}';
    }
}
