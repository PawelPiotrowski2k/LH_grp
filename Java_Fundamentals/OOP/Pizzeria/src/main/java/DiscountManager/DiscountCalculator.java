package DiscountManager;
import Customer.Customer;
import Customer.CustomerType;
import Pizza.Pizza;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Map;

public class DiscountCalculator {

    private   DiscountCalculator() {
    }

    public static double getFinalPrcieFromOrderWithDiscount(Customer customer, Map<Pizza, Integer> MapOfPizzasWithQuantity) {
        double priceWithDiscount = 0;
        for (Map.Entry<Pizza, Integer> entry : MapOfPizzasWithQuantity.entrySet()
        ) {
            Pizza pizza = entry.getKey();
            Integer quantity = entry.getValue();
            priceWithDiscount = pizza.getPrice() * quantity + priceWithDiscount;
        }
        CustomerType customerType = customer.getCustomerType();
        switch (customerType) {
            case CHILD:
                if (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
                    priceWithDiscount = priceWithDiscount * customerType.getValue();
                }
                break;
            case STUDENT:
                if (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
                    priceWithDiscount = priceWithDiscount * customerType.getValue();
                }
                break;
            default:
                break;
        }
        return priceWithDiscount;
    }
}
