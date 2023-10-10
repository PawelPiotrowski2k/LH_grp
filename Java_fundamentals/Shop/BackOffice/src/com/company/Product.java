package com.company;
public class Product {
    private final String name;
    private final String catergory;
    private double price;
    private final double tax;
    private final double discount;
    private final int id;


    public Product(String name, String catergory, double price, int id, double discount) {
        this.name = name;
        this.catergory = catergory;
        this.price = price;
        this.tax = 0.77;
        this.id = id;
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public String getCatergory(){
        return catergory;
    }

    public String getName(){
        return name;
    }

    public double finalPrice(){
        return this.price * this.discount * this.tax;
    }
}
