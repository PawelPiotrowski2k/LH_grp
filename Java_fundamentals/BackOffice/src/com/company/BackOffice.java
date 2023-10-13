package com.company;

import java.util.ArrayList;
import java.util.List;


public class BackOffice {


public static void listOfProducts(ArrayList<Product> products){
        int index = 1;
    System.out.println("lista dostępnych produktów: ");
    for (Product product :
            products) {
    System.out.println(index + " " + product.getName() + " " + product.getCatergory());
        index++;
    }
    System.out.println();
}
public static void listOfDiscount(){
    for (Discount discount :
            Discount.values()) {
        System.out.println( discount.name() + " " + discount.getDiscountValue());
    }
}
public void finishOrder(Cart cart){
        Order order = new Order(cart.getListOfProducts(),"PIATUNIO");
        cart = null;
}
public List<Order> finishedCarts(ArrayList<Order> finishedOrder){
    return finishedOrder;
}
}
