import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Backoffice {
    private List<Product> allProducts = new ArrayList<Product>();
    private List<Order> orders = new ArrayList<Order>();
    private List<Cart> finishedCarts = new ArrayList<Cart>();


    public List<Order> getOrders() {
        return orders;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }
    public List<Cart> getFinishedCarts() {
        return finishedCarts;
    }
    public void addProductToAllProducts(Product product){
        allProducts.add(product);
    }
    public Map<String, String> listOfProducts(List<Product> products){
        Map<String, String> productsWithCategory = new HashMap<>();
        for (Product product :
                products) {
            productsWithCategory.put(product.getName(),product.getCatergory());
        }
        return productsWithCategory;
    }
    public HashMap<String, Double> listOfDiscount(){
        HashMap<String, Double> discounts = new HashMap<String, Double>();
        for (Discount discount :
                Discount.values()) {
            discounts.put(discount.name(), discount.getDiscountValue());
        }
        return discounts;
    }
    public void finishOrder(Cart cart){
        Order order = new Order(cart);
        orders.add(order);
        finishedCarts.add(cart);
    }
    public List<Cart> finishedCarts(List<Cart> carts){
        return carts;
    }
}
