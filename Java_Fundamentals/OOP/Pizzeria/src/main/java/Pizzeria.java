import Cook.Cook;
import Customer.Customer;
import Order.OrderPreparation;
import Pizza.*;
import Order.OrderProcedure;
import ingredient.Ingredient;
import ingredient.IngredientsMonitor;

import java.util.Map;
import java.util.Set;


public class Pizzeria {
    private final Set<Customer> setOfCustomers;
    private final Set<Pizza> setOfPizzas;
    private final OrderProcedure orderProcedure;
    private final IngredientsMonitor ingredientsMonitor;
    private final OrderPreparation orderPreparation;

    public Pizzeria(IngredientsMonitor ingredientsMonitor,OrderPreparation orderPreparation, Set<Customer> setOfCustomer, Set<Pizza> setOfPizzas, OrderProcedure orderProcedure) {
        this.setOfCustomers = setOfCustomer;
        this.setOfPizzas = setOfPizzas;
        this.orderProcedure = orderProcedure;
        this.ingredientsMonitor = ingredientsMonitor;
        this.orderPreparation = orderPreparation;
    }

    public void createOrder(Map<Pizza, Integer> mapOfPizzasWithQuantity, boolean takeAway, Customer customer) {
      orderProcedure.createOrder(mapOfPizzasWithQuantity, takeAway, customer);
    }
    public void checkIngredients(){
        ingredientsMonitor.checkIngredients();
    }

    public void addCook(Cook cook) {
        orderPreparation.addCook(cook);
    }

    public void addIngredient(Ingredient ingredient, int minQuantity) {
        ingredientsMonitor.addIngredient(ingredient, minQuantity);
    }

    public void addCustomer(Customer customer) {
        setOfCustomers.add(customer);
    }

    public void addPizza(Pizza pizza) {
        setOfPizzas.add(pizza);
    }
}
