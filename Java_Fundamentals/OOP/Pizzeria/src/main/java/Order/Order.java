package Order;

import Models.Customer;
import Models.CustomerType;
import Models.Pizza;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final Map<Pizza, Integer> mapOfPizzasWithQuantity;
    private final CustomerType customerType;
    private final boolean takeAway;
    private double finalPrcie;

    public Order(Map<Pizza, Integer> mapOfPizzasWithQuantity, boolean takeAway, Customer customer) {
        this.orderId = UUID.randomUUID().toString();
        this.mapOfPizzasWithQuantity = mapOfPizzasWithQuantity;
        this.customerType = customer.getCustomerType();
        this.takeAway = takeAway;
        this.finalPrcie = getFinalPrcie(customer.getCustomerType());
    }


    public String getOrderId() {
        return orderId;
    }

    public Map<Pizza, Integer> getMapOfPizzasWithQuantity() {
        return mapOfPizzasWithQuantity;
    }

    private double getFinalPrcie(CustomerType customerType) {
        double priceWithDiscount = 0;
        double childDiscount = 0.9;
        double studentDiscount = 0.6;
        for (Map.Entry<Pizza, Integer> entry : mapOfPizzasWithQuantity.entrySet()
             ) {
            Pizza pizza = entry.getKey();
            Integer quantity = entry.getValue();
            priceWithDiscount = pizza.getPrice() * quantity + priceWithDiscount;
        }
        switch (customerType) {
            case CHILD:
                if (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
                    priceWithDiscount = finalPrcie * childDiscount;
                }
                break;
            case STUDENT:
                if (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
                    priceWithDiscount = finalPrcie * studentDiscount;
                }
                break;
            default:
                break;
        }
        return priceWithDiscount;
    }
}
