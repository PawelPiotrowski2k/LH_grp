import Models.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pizzeria {
    private final List<Order> listOfOrders;
    private final Set<Customer> setOfCustomers;
    private final Set<Cook> setOfCooks;
    private final Set<Pizza> setOfPizzas;
    private final Set<Ingredients> setOfIngredients;
    private final OrderProcedure orderProcedure;
    private final TableManager tableManager;
    private final IngredientsMonitor ingredientsMonitor;

    public Pizzeria(IngredientsMonitor ingredientsMonitor, List<Order> listOfOrders, Set<Customer> setOfCustomer, Set<Cook> setOfCooks, Set<Pizza> setOfPizzas, OrderProcedure orderProcedure, TableManager tableManager, Set<Ingredients> setOfIngredients) {
        this.listOfOrders = listOfOrders;
        this.setOfCustomers = setOfCustomer;
        this.setOfCooks = setOfCooks;
        this.setOfPizzas = setOfPizzas;
        this.orderProcedure = orderProcedure;
        this.tableManager = tableManager;
        this.setOfIngredients = setOfIngredients;
        this.ingredientsMonitor = ingredientsMonitor;
    }

    public void createOrder(List<Pizza> listOfPizzas, CustomerType customerType, boolean takeAway, Customer customer) {
        Order order = new Order(listOfPizzas, customerType, takeAway);
        if (!takeAway && !tableManager.assignCustomerToTable()) {
            System.out.println("no more free tables, the order has been canceled");
        } else {
            listOfOrders.add(order);
            orderProcedure.addOrder(order);
            customer.eating();
            tableManager.cleanTable();
            ingredientsMonitor.checkIngredients(setOfIngredients);
        }
    }

    public void addCook(String name) {
        Cook cook = new Cook(name);
        setOfCooks.add(cook);
        orderProcedure.addFreeCook(cook);
    }

    public void addIngredient(int minQuantity, String name, int quantityInStock) {
        Ingredients ingredient = new Ingredients(minQuantity, name, quantityInStock);
        setOfIngredients.add(ingredient);
    }

    public void addCustomer(String name, CustomerType customerType) {
        Customer customer = new Customer(name, customerType);
        setOfCustomers.add(customer);
    }

    public void createPizza(int price, String name, Map<Ingredients, Integer> ingredientsNeeded) {
        Pizza pizza = new Pizza(price, name, ingredientsNeeded);
        setOfPizzas.add(pizza);
    }

}
