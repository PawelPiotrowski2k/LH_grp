import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void finalPrice() {
        Product mleko = new Product("mleko", "nabial", 3.50,0.9);
        double finalPrice = mleko.getFinalPrice();
        assertEquals(3.87,finalPrice);
    }
}