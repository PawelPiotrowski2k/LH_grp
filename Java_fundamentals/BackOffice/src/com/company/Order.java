package com.company;

import java.util.ArrayList;

public class Order {
    private final ArrayList<Product> products = new ArrayList<Product>();
    final int id;
    final double discount;
    private static int lastOrderId = 0;

    public Order(ArrayList<Product> listOfProducts,String discountName) {
        this.id = ++lastOrderId;
        this.products.addAll(listOfProducts);
        Discount discount1 = Discount.valueOf(discountName);
        this.discount = discount1.getDiscountValue();
    }
    public double finalPrice(){
        double price = 0.0;

        for (Product product :
                products) {
            price = price + product.finalPrice();
        }
        return price * discount;
    }
}
