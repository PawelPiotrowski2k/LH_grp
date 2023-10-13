import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BackofficeTest {

    @Test
    void testlistOfProducts() {
        var mleko = new Product("mleko","nabial",7.00,1.00);
        var chleb = new Product("chleb","pieczywo",7.00,1.00);
        var backoffice = new Backoffice();
        backoffice.addProductToAllProducts(mleko);
        backoffice.addProductToAllProducts(chleb);
        Map<String, String> products = backoffice.listOfProducts(backoffice.getAllProducts());
        String key = products.get("chleb");
        assertEquals("pieczywo",key);
        assertEquals(2, backoffice.listOfProducts(backoffice.getAllProducts()).size());
    }

    @Test
    void listOfDiscount() {
    var discountProvider = new Backoffice();
    HashMap<String, Double> discounts = discountProvider.listOfDiscount();
    assertNotNull(discounts);
    assertFalse(discounts.isEmpty());
    assertEquals(Discount.values().length, discounts.size());
    for(Discount discount: Discount.values()){
        assertTrue(discounts.containsKey(discount.name()));
    }
    for(Double discountValue : discounts.values()){
        assertTrue(discountValue >= 0);
    }
    }
    @Test
    void finishOrder() {
        Cart cart = new Cart("PIATUNIO");
        var mleko = new Product("mleko","nabial",7.00,1.00);
        var chleb = new Product("chleb","pieczywo",7.00,1.00);
        cart.addProduct(mleko);
        var backoffice = new Backoffice();
        backoffice.finishOrder(cart);
        cart.addProduct(chleb);
        backoffice.finishOrder(cart);
        assertTrue(2 == backoffice.getOrders().size());
        for (Order order :
                backoffice.getOrders()) {
            assertEquals("mleko",order.getProducts().get(0).getName());
        }
            assertEquals("chleb", backoffice.getOrders().get(1).getProducts().get(1).getName());
    }
    @Test
    void finishedCarts() {
        Cart cart = new Cart("PIATUNIO");
        var mleko = new Product("mleko","nabial",7.00,1.00);
        Backoffice backoffice = new Backoffice();
        cart.addProduct(mleko);
        backoffice.finishOrder(cart);
        backoffice.finishedCarts(backoffice.getFinishedCarts());
        assertEquals("mleko",backoffice.getFinishedCarts().get(0).getListOfProducts().get(0).getName());
        assertNotNull(backoffice.getFinishedCarts());
    }
}