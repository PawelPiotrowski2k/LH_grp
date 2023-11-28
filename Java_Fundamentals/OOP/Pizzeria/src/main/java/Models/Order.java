package Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final Map<Pizza, Integer> listOfPizzasWithQuantity;
    private final CustomerType customerType;
    private final boolean takeAway;
    private double finalPrcie;

    public Order(Map<Pizza, Integer> listOfPizzasWithQuantity, boolean takeAway,Customer customer) {
        this.orderId = UUID.randomUUID().toString();
        this.listOfPizzasWithQuantity = listOfPizzasWithQuantity;
        this.customerType = customer.getCustomerType();
        this.takeAway = takeAway;
        this.finalPrcie = getFinalPrcie();
    }


    public String getOrderId() {
        return orderId;
    }

    public Map<Pizza, Integer> getListOfPizzasWithQuantity() {
        return listOfPizzasWithQuantity;
    }

    private double getFinalPrcie() {
        double priceWithDiscount = 0;
        double childDiscount = 0.9;
        double studentDiscount = 0.6;
        for (Map.Entry<Pizza, Integer> entry :listOfPizzasWithQuantity.entrySet()
             ) {
            Pizza pizza = entry.getKey();
            Integer quantity = entry.getValue();
            priceWithDiscount = pizza.getPrice() * quantity + priceWithDiscount;
        }
        switch (LocalDateTime.MAX.getDayOfWeek()) {
            case TUESDAY:
                if (customerType.equals(CustomerType.CHILD)) {
                    priceWithDiscount = finalPrcie * childDiscount;
                }
                break;
            case THURSDAY:
                if (customerType.equals(CustomerType.STUDENT)) {
                    priceWithDiscount = finalPrcie * studentDiscount;
                }
                break;
            default:
                break;
        }
        return priceWithDiscount;
    }
}
