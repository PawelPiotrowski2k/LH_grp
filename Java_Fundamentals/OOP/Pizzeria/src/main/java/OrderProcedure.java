import Models.Cook;
import Models.Ingredients;
import Models.IngredientsMonitor;
import Models.Order;

import java.util.*;

public class OrderProcedure {
        private final Queue<Order> orderQueue;
        private final Set<Cook> listOfFreeCooks;
        private final Map<Cook,String> listOfCookWithOrder;
        private final IngredientsMonitor ingredientsMonitor;

    public OrderProcedure(Queue<Order> orderQueue, Set<Cook> listOfCooks, Map<Cook, String> listOfCookWithOrder,IngredientsMonitor ingredientsMonitor) {
        this.orderQueue = orderQueue;
        this.listOfFreeCooks = listOfCooks;
        this.listOfCookWithOrder = listOfCookWithOrder;
        this.ingredientsMonitor = ingredientsMonitor;
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
                listOfCookWithOrder.put(cook,orderQueue.poll().getOrderId());
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
