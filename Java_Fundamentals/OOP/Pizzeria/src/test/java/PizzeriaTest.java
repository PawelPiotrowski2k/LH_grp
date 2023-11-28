import Models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

class PizzeriaTest {

    Pizzeria objectUnderTest;
    List<Order> listOfOrders;
    Set<Customer> setOfCustomers;
    Set<Cook> setOfCooks;
    Set<Pizza> setOfPizzas;
    Set<Ingredients> setOfIngredients;
    OrderProcedure orderProcedure;
    TableManager tableManager;
    Queue<Order> orderQueue;
    IngredientsMonitor ingredientsMonitor;




    @BeforeEach
    void setup(){
        orderQueue = new ArrayDeque<>();
        listOfOrders = new ArrayList<>();
        setOfCustomers = new HashSet<>();
        ingredientsMonitor = new IngredientsMonitor();
        setOfCooks = new HashSet<>();
        setOfPizzas = new HashSet<>();
        setOfIngredients = new HashSet<>();
        tableManager = new TableManager(3);
        orderProcedure = new OrderProcedure(orderQueue,setOfCooks);
        objectUnderTest = new Pizzeria(ingredientsMonitor,listOfOrders,setOfCustomers,setOfCooks,setOfPizzas,orderProcedure,tableManager,setOfIngredients);
    }


    @Test
    @DisplayName("Create Order")
    void createOrder(){
        Ingredients cheese = new Ingredients(20,"cheese", 40);
        Ingredients salami = new Ingredients(30,"salami", 50);
        Ingredients mozarella = new Ingredients(20,"mozarella", 70);
        objectUnderTest.addIngredient(cheese);
        objectUnderTest.addIngredient(salami);
        objectUnderTest.addIngredient(mozarella);
        Map<String, Integer> ingredientsOfMargheritta= new HashMap<>();
        ingredientsOfMargheritta.put(cheese.getId(),4);
        ingredientsOfMargheritta.put(mozarella.getId(),5);
        Pizza margheritta = new Pizza(10.2,"margheritta",ingredientsOfMargheritta);
        objectUnderTest.createPizza(margheritta);
        List<Pizza> listOfOrderedPizzas = new ArrayList<>();
        listOfOrderedPizzas.add(margheritta);
        Customer customer = new Customer("Pawel", CustomerType.STUDENT);
//        objectUnderTest.createOrder(listOfOrderedPizzas,false, customer);

    }

}