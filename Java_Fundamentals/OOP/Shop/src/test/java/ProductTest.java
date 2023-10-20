import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product mleko;

    @BeforeEach
    void setup(){
    mleko =new ProductBuilder()
            .setName("mleko").
            setCatergory("nabial").
            setPrice(3.50).
            setTax(0.9).
            build();

}
    @Test
    @DisplayName("Product gives correct final price")
    void finalPrice() {
        //given
        double expectedresult = 3.87;

        // Product mleko


        //when
        double finalPrice = mleko.getFinalPrice();

        //then
        assertEquals(expectedresult,finalPrice);
    }
}