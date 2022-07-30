package com.example.cashregister.entity;

import java.util.Base64;

/**
 * Entity of product
* */
public class Product {

    private int id;
    private String name;
    private int quantity;
    private double weight;
    private double price;
    private String imgbase64;
    private byte[] img;


    public Product(int id, String name, int quantity, double weight, double price,byte[] img) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.price = price;
        this.img=img;
        imgbase64= Base64.getEncoder().encodeToString(img);
    }


    public Product( ) {
        this.id = 0;
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
