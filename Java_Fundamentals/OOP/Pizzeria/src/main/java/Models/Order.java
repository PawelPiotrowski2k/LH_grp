package Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final List<Pizza> listOfPizzas;
    private final CustomerType customerType;
    private final boolean takeAway;
    private double finalPrcie;

    public Order(List<Pizza> listOfPizzas, CustomerType customerType, boolean takeAway) {
        this.orderId = UUID.randomUUID().toString();
        this.listOfPizzas = listOfPizzas;
        this.customerType = customerType;
        this.takeAway = takeAway;
    }


    public String getOrderId() {
        return orderId;
    }

    public List<Pizza> getListOfPizzas() {
        return listOfPizzas;
    }

    private double getFinalPrcie() {
        double pricewithdiscount = 0;
        double childDiscount = 0.9;
        double studentDiscount = 0.6;
        for (Pizza pizaa :
                listOfPizzas) {
            pricewithdiscount += pizaa.getPrice();
        }
        switch (LocalDateTime.MAX.getDayOfWeek()) {
            case TUESDAY:
                if (customerType.equals(CustomerType.CHILD)) {
                    pricewithdiscount = finalPrcie * childDiscount;
                }
                break;
            case THURSDAY:
                if (customerType.equals(CustomerType.STUDENT)) {
                    pricewithdiscount = finalPrcie * studentDiscount;
                }
                break;
            default:
                break;
        }
        return pricewithdiscount;
    }
}
