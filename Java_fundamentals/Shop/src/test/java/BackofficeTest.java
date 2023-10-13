import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BackofficeTest {

    @Test
    void testlistOfProductsGivesCorretCategory() {
        var mleko = new Product("mleko","nabial",7.00,1.00);
        var chleb = new Product("chleb","pieczywo",7.00,1.00);
        var backoffice = new Backoffice();
        backoffice.addProductToAllProducts(mleko);
        backoffice.addProductToAllProducts(chleb);
        Map<String, String> products = backoffice.getListOfProductswithCategory(backoffice.getAllProducts());
        String key = products.get("chleb");
        assertEquals("pieczywo",key);
    }

    @Test
    void listOfDiscountGivesCorrectDiscounts() {
    var discountProvider = new Backoffice();
        List<Discount> discounts = discountProvider.getListOfDiscount();
    for(Discount discount: Discount.values()){
        assertTrue(discounts.contains(discount));
    }
    for(Discount discount : Discount.values()){
        assertTrue(discount.discountValue > 0);
    }
    }

    @Test
    void finishOrderAddProductAfterClosingCart() {
        Cart firstCart = new Cart("PIATUNIO");
        var mleko = new Product("mleko","nabial",7.00,1.00);
        var chleb = new Product("chleb","pieczywo",7.00,1.00);
        firstCart.addProduct(mleko);
        var backoffice = new Backoffice();
        backoffice.createOrder(firstCart);
        firstCart.addProduct(chleb);
        backoffice.createOrder(firstCart);
        assertTrue(1 == backoffice.getFinishedCarts().get(1).getListOfProducts().size());

    }
    @Test
    void finishedCartsGivesCorrectCarts() {
        Cart cart = new Cart("PIATUNIO");
        var mleko = new Product("mleko","nabial",7.00,1.00);
        Backoffice backoffice = new Backoffice();
        cart.addProduct(mleko);
        backoffice.createOrder(cart);
        backoffice.getFinishedCarts();
        assertEquals("mleko",backoffice.getFinishedCarts().get(0).getListOfProducts().get(0).getName());
        assertNotNull(backoffice.getFinishedCarts());
    }
}