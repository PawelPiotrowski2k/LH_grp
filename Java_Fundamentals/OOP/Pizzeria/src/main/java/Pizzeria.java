import java.util.List;

public class Pizzeria {
    List<Order> ordersInQueue;

    public void createOrder(List<Pizza> listOfPizzas, CustomerType customerType, boolean takeAway){
        Order order = new Order(listOfPizzas,customerType,takeAway);
    }
}
