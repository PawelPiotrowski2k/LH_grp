package Order;

import Models.*;
import ingredients.IngredientsMonitor;
import tableManaging.TableManager;

import java.util.*;

public class OrderProcedure {
        private final Queue<Order> orderQueue;
        private final Set<Cook> setOfCooks;
        private final TableManager tableManager;
        private final IngredientsMonitor ingredientsMonitor;


    public OrderProcedure(Queue<Order> orderQueue, Set<Cook> setOfCooks, TableManager tableManager, IngredientsMonitor ingredientsMonitor) {
        this.orderQueue = orderQueue;
        this.setOfCooks = setOfCooks;
        this.tableManager = tableManager;
        this.ingredientsMonitor = ingredientsMonitor;
    }

    /**
     * x.addOrder (Set<Order> orders -> orders.add(order))
     * checkIfAnyOrderToPrepare
     * @param order
     */
    public void addOrder(Order order){
        if(setOfCooks.isEmpty()){
            orderQueue.add(order);
        }else {
            assignCooksToOrders();
        }
    }

    public void createOrder(Map<Pizza, Integer> mapOfPizzasWithQuantity, boolean takeAway, Customer customer) {
        if (!takeAway && !tableManager.assignCustomerToTable() && !ingredientsMonitor.checkIfThereIsEnoughIngredients(mapOfPizzasWithQuantity)) {
            System.out.println("the order has been canceled");
            return;
        }
        Order order = new Order(mapOfPizzasWithQuantity, takeAway, customer);
        addOrder(order);
        ingredientsMonitor.subIngredientUsedInOrder(order.getMapOfPizzasWithQuantity());
        tableManager.cleanTable();
        ingredientsMonitor.checkIngredients();
    }

    public void procedOrder(Order order){
        ingredientsMonitor.subIngredientUsedInOrder(order.getMapOfPizzasWithQuantity());
        tableManager.cleanTable();
        ingredientsMonitor.checkIngredients();
    }

    
    //poniżej powinno być w czymś osobnym
    public void addCook(Cook cook){
        setOfCooks.add(cook);
    }
    public void removeCookFromFreeCookList(Cook cook){
        setOfCooks.remove(cook);
    }
    private void assignCooksToOrders() {
        for (Cook cook : setOfCooks) {
            if (!orderQueue.isEmpty()) {
                removeCookFromFreeCookList(cook);
                cook.prepareFood();
                addCook(cook);
            }else {
                break;
            }
        }
    }
}