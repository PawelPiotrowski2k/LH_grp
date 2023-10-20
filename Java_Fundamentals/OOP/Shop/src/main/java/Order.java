import java.util.*;

public class Order {
    private final HashSet<Product> products;
    private final String id;
    private final Discount discount;
    private final double finalPrice;

    public Order (Set<Product> products, Discount discount, double finalPrice, String id) {
        this.products = new HashSet<Product>(products);
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.id = id;
    }
    public static Order CreatingOrderFromCart(Cart cart){
        Set<Product> products = new HashSet<Product>(cart.getListOfProducts());
        Discount discount = cart.getDiscount();
        double finalPrice = cart.finalPrice();
        String id = UUID.randomUUID().toString();
        return new Order(products,discount,finalPrice,id);
    }

    public double finalPrice() {
        return finalPrice;
    }
}
