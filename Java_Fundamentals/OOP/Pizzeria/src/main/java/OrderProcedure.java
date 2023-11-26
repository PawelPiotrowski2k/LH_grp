import Models.Cook;
import Models.IngredientsMonitor;
import Models.Order;

import java.util.*;

public class OrderProcedure {
        private final Queue<Order> orderQueue;
        private final Set<Cook> listOfFreeCooks;
        private final Map<Cook,String> mapOfCookWithOrder;


    public OrderProcedure(Queue<Order> orderQueue, Set<Cook> listOfCooks, Map<Cook, String> mapOfCookWithOrder) {
        this.orderQueue = orderQueue;
        this.listOfFreeCooks = listOfCooks;
        this.mapOfCookWithOrder = mapOfCookWithOrder;

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
                mapOfCookWithOrder.put(cook,orderQueue.poll().getOrderId());
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
