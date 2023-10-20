import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cart {

    private final Set<Product> listOfProducts = new HashSet<Product>();
    private final Discount discount;
    private boolean closed;


    public Cart(Discount discount) {
        this.discount = discount;
        this.closed = false;
    }

    public double finalPrice() {
        double finalPrice = listOfProducts.stream().mapToDouble(Product::getFinalPrice).sum();
        return Math.round(finalPrice * discount.getDiscountValue() * 100.0) / 100.0;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void addProduct(Product product) {
        if (!closed)
            listOfProducts.add(product);
    }

    public Set<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void closeCart() {
        closed = true;
    }
}
