import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private final ArrayList<Product> products = new ArrayList<Product>();
    private final String id;
    private final double discount;
    private final double finalPrice;

    public Order(Cart cart) {
        this.products.addAll(cart.getListOfProducts());
        this.discount = cart.getDiscount();
        this.finalPrice = cart.finalPrice();
        this.id = UUID.randomUUID().toString();
    }

    public double finalPrice() {
        return finalPrice;
    }
}
