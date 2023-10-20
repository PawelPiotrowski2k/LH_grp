import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Backoffice {
    private final List<Product> allProducts = new ArrayList<Product>();
    private final List<Order> orders = new ArrayList<Order>();
    private final List<Cart> finishedCarts = new ArrayList<Cart>();


    public List<Product> getAllProducts() {
        return allProducts;
    }

    public List<Cart> getFinishedCarts() {
        return finishedCarts;
    }

    public void addProductToAllProducts(Product product) {
        allProducts.add(product);
    }

    public Map<String, String> getListOfProductswithCategory(List<Product> products) {
        Map<String, String> productsWithCategory = new HashMap<String, String>();
        for (Product product : products) {
            productsWithCategory.compute(product.getName(),(key, value) ->
                    (value == null) ? product.getCatergory() : value + ", " + product.getCatergory());
        }
        return productsWithCategory;
    }

    public List<Discount> getListOfDiscount() {
        return Discount.getListOfDiscount();
    }

    public void createOrder(Cart cart) {
        Order order = Order.CreatingOrderFromCart(cart);
        orders.add(order);
        finishedCarts.add(cart);
        cart.closeCart();
    }
}
