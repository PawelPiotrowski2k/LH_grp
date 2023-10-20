import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {



    private Cart objectUnderTest;
    Discount piatunioDiscount;
    Discount hurtowaDiscount;
    private String mlekoName;
    private String chlebName;
    private String mlekoCategory;
    private String chlebCategory;
    private double mlekoPrice;
    private double chlebPrice;
    private double tax;
    private double discount;
    private double discountWithoutDiscount;
    private Product mleko;
    private Product chleb;


    @BeforeEach
    void setUp() {
        piatunioDiscount = Discount.PIATUNIO;
        hurtowaDiscount = Discount.HURTOWA;
        String mlekoName = "mleko";
        String chlebName = "chleb";
        String mlekoCategory = "nabial";
        String chlebCategory = "pieczywo";
        double mlekoPrice = 3.20;
        double chlebPrice = 3.00;
        double tax = 1.23;
        double discount = 0.9;
        double discountWithoutDiscount = 1.0;
        mleko = new ProductBuilder().setName(mlekoName)
                .setCatergory(mlekoCategory)
                .setPrice(mlekoPrice)
                .setTax(tax)
                .setDiscount(discount)
                .build();
        chleb = new ProductBuilder().setName(chlebName)
                .setCatergory(chlebCategory)
                .setPrice(chlebPrice)
                .setTax(tax)
                .setDiscount(discount)
                .build();


    }

    @Test
    void finalPrice() {
        //Given
        double expectedValue = 6.17;
        objectUnderTest = new Cart(piatunioDiscount);
        //Products mleko and chleb, cart with PIATUNIO discount

        //When

        objectUnderTest.addProduct(mleko);
        objectUnderTest.addProduct(chleb);
        //Then
        assertEquals(expectedValue, objectUnderTest.finalPrice());
    }

    @Test
    void finalPriceWithOtherDiscount(){
        //Given
        double expectedValue = 4.80;
        objectUnderTest = new Cart(hurtowaDiscount);
        //Products mleko and chleb, cart with PIATUNIO discount

        //When

        objectUnderTest.addProduct(mleko);
        objectUnderTest.addProduct(chleb);
        //Then
        assertEquals(expectedValue, objectUnderTest.finalPrice());

    }
}