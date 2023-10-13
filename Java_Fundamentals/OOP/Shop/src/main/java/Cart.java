import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Product> listOfProducts = new ArrayList<Product>();
    private final double discount;
    private boolean closed;


    public Cart(String nameDiscount) {
        Discount cartDiscount = Discount.valueOf(nameDiscount);
        this.discount = cartDiscount.getDiscountValue();
        this.closed = false;
    }

    public double finalPrice() {
        double finalPrice = listOfProducts.stream().mapToDouble(Product::getFinalPrice).sum();
        return Math.round(finalPrice * discount * 100.0) / 100.0;
    }

    public double getDiscount() {
        return discount;
    }

    public void addProduct(Product product) {
        if (!closed)
            listOfProducts.add(product);
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void closeCart() {
        closed = true;
    }
}
