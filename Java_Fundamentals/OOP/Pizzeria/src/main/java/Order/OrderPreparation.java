package Order;

import Cook.Cook;
import Table.TableManager;

import java.util.Queue;
import java.util.Set;

public class OrderPreparation {
    private final Set<Cook> setOfCooks;
    private final Queue<Order> orderQueue;

    public OrderPreparation(Set<Cook> setOfCooks, Queue<Order> orderQueue) {
        this.setOfCooks = setOfCooks;
        this.orderQueue = orderQueue;
    }

    public boolean checkIfAnyCookIsFree() {
        for (Cook cook : setOfCooks) {
            if (!cook.isBussy()) {
                return true;
            }
        }
        return false;
    }

    public Cook returnFreeCook() {
        for (Cook cook : setOfCooks) {
            if (!cook.isBussy()) {
                return cook;
            }
        }
        return null;
    }

    public void addCook(Cook cook) {
        setOfCooks.add(cook);
    }

    public void endPreparingFood(Cook cook) {
        cook.setBussy(false);
        if (!orderQueue.isEmpty()) {
            assignCookToOrder(cook,orderQueue.poll());
        }
    }

    public void assignCookToOrder(Cook cook, Order order) {
        cook.setBussy(true);
        orderQueue.remove(order);
        endPreparingFood(cook);
    }

    public void addOrderToQueIfNoFreeCook(Order order) {
        if (!checkIfAnyCookIsFree()) {
            orderQueue.add(order);
        }else
            assignCookToOrder(returnFreeCook(),order);
    }


}

