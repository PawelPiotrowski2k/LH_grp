import Models.*;
import Order.OrderProcedure;
import ingredients.Ingredients;
import ingredients.IngredientsMonitor;

import java.util.Map;
import java.util.Set;

/**
 * Order:
 *  OrderService
 *  OrderRepo
 *  ....
 * Table:
 *    TableDto/TableModel
 *    TableService
 *    .....
 *
 *
 */
public class Pizzeria {
    private final Set<Customer> setOfCustomers;
    private final Set<Pizza> setOfPizzas;
    private final OrderProcedure orderProcedure;
    private final IngredientsMonitor ingredientsMonitor;

    public Pizzeria(IngredientsMonitor ingredientsMonitor, Set<Customer> setOfCustomer, Set<Pizza> setOfPizzas, OrderProcedure orderProcedure) {
        this.setOfCustomers = setOfCustomer;
        this.setOfPizzas = setOfPizzas;
        this.orderProcedure = orderProcedure;
        this.ingredientsMonitor = ingredientsMonitor;
    }

    public void createOrder(Map<Pizza, Integer> mapOfPizzasWithQuantity, boolean takeAway, Customer customer) {
      orderProcedure.createOrder(mapOfPizzasWithQuantity, takeAway, customer);
    }
    public void checkIngredients(){
        ingredientsMonitor.checkIngredients();
    }

    public void addCook(Cook cook) {
        orderProcedure.addCook(cook);
    }

    public void addIngredient(Ingredients ingredient) {
        ingredientsMonitor.addIngredient(ingredient);
    }

    public void addCustomer(Customer customer) {
        setOfCustomers.add(customer);
    }

    public void addPizza(Pizza pizza) {
        setOfPizzas.add(pizza);
    }
}
