import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Backoffice {
    private List<Product> allProducts = new ArrayList<Product>();
    private List<Order> orders = new ArrayList<Order>();
    private List<Cart> finishedCarts = new ArrayList<Cart>();


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
        Map<String, String> productsWithCategory = new HashMap<>();
        for (Product product :
                products) {
            productsWithCategory.put(product.getName(), product.getCatergory());
        }
        return productsWithCategory;
    }

    public List<Discount> getListOfDiscount() {
        return Discount.getListOfDiscount();
    }

    public void createOrder(Cart cart) {
        Order order = new Order(cart);
        orders.add(order);
        finishedCarts.add(cart);
        cart.closeCart();
    }
}
