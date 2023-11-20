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

    public double getFinalPrcie() {
        calculateFinalPrice();
        return finalPrcie;
    }

    private void calculateFinalPrice() {
        for (Pizza pizaa :
                listOfPizzas) {
            this.finalPrcie += pizaa.getPrice();
        }
        switch (LocalDateTime.MAX.getDayOfWeek()) {
            case TUESDAY:
                if (customerType.equals(CustomerType.CHILD)) {
                    finalPrcie = finalPrcie * 0.9;
                }
                break;
            case THURSDAY:
                if (customerType.equals(CustomerType.STUDENT)) {
                    finalPrcie = finalPrcie * 0.6;
                }
            default:
                break;
        }
    }


}
