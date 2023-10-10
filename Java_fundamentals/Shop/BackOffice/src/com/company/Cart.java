package com.company;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart {
    private final int id;
    private ArrayList<Product> listOfProducts = new ArrayList<Product>();
    private final double discount;


    public String finalPrice(double discount){
        DecimalFormat df = new DecimalFormat("#.##");
        double finalPrice = 0;
        for (Product product :
                listOfProducts) {
            finalPrice = finalPrice + product.finalPrice();
        }
        return df.format(finalPrice * this.discount);
    }
    public Cart(int id) {
        this.id = id;
        this.discount = 1;
    }
    public void addProduct (Product product){
            listOfProducts.add(product);
    }
    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }
}
