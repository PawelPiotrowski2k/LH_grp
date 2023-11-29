import Models.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pizzeria {
    private final Set<Customer> setOfCustomers;
    private final Set<Pizza> setOfPizzas;
    //powyżej nie powinno być tutaj
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

    public void addCook(String name) {
        Cook cook = new Cook(name);
        orderProcedure.addCook(cook);
    }

    public void addIngredient(Ingredients ingredient) {
        ingredientsMonitor.addIngredient(ingredient);
    }

    public void addCustomer(String name, CustomerType customerType) {
        Customer customer = new Customer(name, customerType);
        setOfCustomers.add(customer);
    }

    public void createPizza(Pizza pizza) {
        setOfPizzas.add(pizza);
    }

}
