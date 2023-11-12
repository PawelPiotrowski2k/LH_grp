public class OrderCreator {
    private final String orderId;
    private final List<Pizza> listOfPizzas;

    public OrderCreator(String orderId, List<Pizza> listOfPizzas) {
        this.orderId = orderId;
        this.listOfPizzas = listOfPizzas;
    }
}
