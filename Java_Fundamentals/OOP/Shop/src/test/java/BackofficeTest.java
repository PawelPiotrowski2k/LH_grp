import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BackofficeTest {

    private Cart objectUnderTest;
    private Discount piatunioDiscount;
    private Discount hurtowaDiscount;
    private Discount swiatecznaDiscount;
    private Backoffice backoffice;

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
        swiatecznaDiscount = Discount.SWIATECZNA;
        mlekoName = "mleko";
        chlebName = "chleb";
        mlekoCategory = "nabial";
        chlebCategory = "pieczywo";
        mlekoPrice = 3.20;
        chlebPrice = 3.00;
        tax = 1.23;
        discount = 0.9;
        discountWithoutDiscount = 1.0;
        backoffice = new Backoffice();
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
    void testlistOfProductsGivesCorretCategory() {
        //Given
        //When
        backoffice.addProductToAllProducts(mleko);
        backoffice.addProductToAllProducts(chleb);
        Map<String, String> products = backoffice.getListOfProductswithCategory(backoffice.getAllProducts());
        String key = products.get("chleb");
        //Then
        assertEquals("pieczywo", key);
    }

    @ParameterizedTest
    @EnumSource(Discount.class)
    void listOfDiscountGivesCorrectDiscounts(Discount discount) {
        //Given
        //When
        List<Discount> discounts = backoffice.getListOfDiscount();
        //Then
        assertTrue(discount.getDiscountValue() < 1, "wartość " + discounts + " nie jest mniejsza niż 1");
        }


    @Test
    void finishOrderAddProductAfterClosingCart() {
        //Given
        objectUnderTest = new Cart(hurtowaDiscount);
        //When
        objectUnderTest.addProduct(mleko);
        backoffice.createOrder(objectUnderTest);
        objectUnderTest.closeCart();
        objectUnderTest.addProduct(chleb);
        List<Product> productsOfFinishedCart = new ArrayList<Product>(backoffice.getFinishedCarts().get(0).getListOfProducts());
        //Then
        assertFalse(productsOfFinishedCart.contains(chleb));



    }

    @Test
    void finishedCartsGivesCorrectCarts() {
        //Given
        objectUnderTest = new Cart(piatunioDiscount);
        //When
        objectUnderTest.addProduct(mleko);
        Backoffice backoffice = new Backoffice();
        objectUnderTest.addProduct(mleko);
        backoffice.createOrder(objectUnderTest);
        List<Product> listOfProductsInFinishedCart = new ArrayList<Product>(backoffice.getFinishedCarts().get(0).getListOfProducts());
        //Then
        assertTrue(listOfProductsInFinishedCart.contains(mleko));
    }
}