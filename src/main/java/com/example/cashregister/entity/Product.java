package com.example.cashregister.entity;

import java.io.Serializable;
import java.util.Base64;

/**
 * Entity of product
 */
public class Product implements Serializable {

    private final int id;
    private final String name;
    private final int quantity;
    private final double weight;
    private final double price;
    private final String imgbase64;

    private int number_in_receipt;



    public Product(int id, String name, int quantity, double weight, double price, byte[] img) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.price = price;
        imgbase64 = Base64.getEncoder().encodeToString(img);
    }

    public Product(int number_in_receipt, int id, String name, int quantity, double weight, double price, byte[] img) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.price = price;
        imgbase64 = Base64.getEncoder().encodeToString(img);
        this.number_in_receipt = number_in_receipt;
    }


    public String getImgbase64() {
        return imgbase64;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public int getNumberInReceipt() {return number_in_receipt;}
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
