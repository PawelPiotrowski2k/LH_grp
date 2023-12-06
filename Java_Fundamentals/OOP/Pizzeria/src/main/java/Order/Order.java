package Order;

import DiscountManager.DiscountCalculator;
import Customer.Customer;
import Pizza.Pizza;

import java.util.Map;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final Map<Pizza, Integer> mapOfPizzasWithQuantity;
    private final boolean takeAway;
    private final Customer customer;
    private double finalPrcie;

    public Order(Map<Pizza, Integer> mapOfPizzasWithQuantity, boolean takeAway, Customer customer) {
        this.orderId = UUID.randomUUID().toString();
        this.mapOfPizzasWithQuantity = mapOfPizzasWithQuantity;
        this.takeAway = takeAway;
        this.customer = customer;
        this.finalPrcie = DiscountCalculator.getFinalPrcieFromOrderWithDiscount(customer,mapOfPizzasWithQuantity);
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Pizza, Integer> getMapOfPizzasWithQuantity() {
        return mapOfPizzasWithQuantity;
    }


}
