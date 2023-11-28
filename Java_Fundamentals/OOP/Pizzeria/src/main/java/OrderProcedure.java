import Models.Cook;
import Models.Order;

import java.util.*;

public class OrderProcedure {
        private final Queue<Order> orderQueue;
        private final Set<Cook> listOfFreeCooks;


    public OrderProcedure(Queue<Order> orderQueue, Set<Cook> listOfCooks) {
        this.orderQueue = orderQueue;
        this.listOfFreeCooks = listOfCooks;
    }

    public void addOrder(Order order){
        if(listOfFreeCooks.isEmpty()){
            orderQueue.add(order);
        }else {
            assignCooksToOrders();

        }
    }
    private void assignCooksToOrders() {
        for (Cook cook : listOfFreeCooks) {
            if (!orderQueue.isEmpty()) {
                removeCookFromFreeCookList(cook);
                cook.prepareFood();
                addFreeCook(cook);
            }else {
                break;
            }
        }
    }
    public void addFreeCook(Cook cook){
        listOfFreeCooks.add(cook);
    }
    public void removeCookFromFreeCookList(Cook cook){
        listOfFreeCooks.remove(cook);
    }
}
