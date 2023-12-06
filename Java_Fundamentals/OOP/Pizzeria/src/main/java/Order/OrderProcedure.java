package Order;

import Cook.Cook;
import Customer.Customer;
import Pizza.*;
import ingredient.IngredientsMonitor;
import Table.TableManager;

import java.util.*;

public class OrderProcedure {
        private final TableManager tableManager;
        private final IngredientsMonitor ingredientsMonitor;
        private final OrderPreparation orderPreparation;


    public OrderProcedure(TableManager tableManager, IngredientsMonitor ingredientsMonitor,OrderPreparation orderPreparation) {

        this.tableManager = tableManager;
        this.ingredientsMonitor = ingredientsMonitor;
        this.orderPreparation = orderPreparation;
    }


    public void createOrder(Map<Pizza, Integer> mapOfPizzasWithQuantity, boolean takeAway, Customer customer) {
        if (!takeAway && !tableManager.assignCustomerToTable() && !ingredientsMonitor.checkIfThereIsEnoughIngredients(mapOfPizzasWithQuantity)) {
            System.out.println("the order has been canceled");
            return;
        }
        Order order = new Order(mapOfPizzasWithQuantity, takeAway, customer);
        orderPreparation.addOrderToQueIfNoFreeCook(order);
        procedOrder(order);
    }

    public void procedOrder(Order order){
        ingredientsMonitor.subIngredientUsedInOrder(order.getMapOfPizzasWithQuantity());
        tableManager.cleanTable();
        ingredientsMonitor.checkIngredients();
    }
}


