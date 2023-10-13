package com.company;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart {
    private final int id;
    private static int lastCartId = 0;
    private ArrayList<Product> listOfProducts = new ArrayList<Product>();
    private final double discount;

    public Cart(String nameDiscount) {
        this.id = ++lastCartId;
        Discount discount1 = Discount.valueOf(nameDiscount);
        this.discount = discount1.getDiscountValue();
    }
    public String finalPrice(double discount){
        DecimalFormat df = new DecimalFormat("#.##");
        double finalPrice = 0;
        for (Product product :
                listOfProducts) {
            finalPrice = finalPrice + product.finalPrice();
        }
        return df.format(finalPrice * this.discount);
    }
    public double getDiscount(){
        return discount;
    }
    public void addProduct (Product product){
            listOfProducts.add(product);
    }
    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }
}
