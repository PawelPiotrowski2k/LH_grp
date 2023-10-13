import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void finalPrice() {
        var mleko = new Product("mleko","nabial",7.00,1.00);
        var chleb = new Product("chleb","pieczywo",7.00,1.00);
        var cart = new Cart("PIATUNIO");
        cart.addProduct(mleko);
        cart.addProduct(chleb);
    }
}