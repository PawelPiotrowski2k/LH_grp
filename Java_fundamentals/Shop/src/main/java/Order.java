

import java.util.ArrayList;

public class Order {
    private final ArrayList<Product> products = new ArrayList<Product>();
    final int id;
    final double discount;
    final double finalPrice;
    private static int lastOrderId = 0;

    public Order(Cart cart) {
        this.id = ++lastOrderId;
        this.products.addAll(cart.getListOfProducts());
        this.discount = cart.getDiscount();
        this.finalPrice = cart.finalPrice();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double finalPrice(){
       return finalPrice;
    }
}
