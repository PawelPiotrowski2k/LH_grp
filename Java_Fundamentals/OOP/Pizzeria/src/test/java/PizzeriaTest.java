import Customer.Customer;
import Order.Order;
import Order.OrderPreparation;
import Order.OrderProcedure;
import Pizza.Pizza;
import Table.TableManager;
import Ingredient.Ingredient;
import Ingredient.IngredientsMonitor;
import Cook.Cook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Customer.CustomerType;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class PizzeriaTest {
    Pizzeria pizzeria;
    OrderProcedure orderProcedure;
    IngredientsMonitor ingredientsMonitor;
    OrderPreparation orderPreparation;
    Customer customer;
    Set<Customer> setOfCustomer;
    Set<Pizza> setOfPizza;
    TableManager tableManager;
    Set<Ingredient> setOfIngredient;
    Map<Ingredient,Integer> mapOfMinQuantityOfIngredient;
    Set<Cook> setOfCooks;
    Queue<Order> queueOfOrders;
    static int NUMBER_OF_TABLES = 20;



    @BeforeEach
    void setup (){
        setOfIngredient = new HashSet<>();
        mapOfMinQuantityOfIngredient = new HashMap<>();
        setOfCooks = new HashSet<>();
        setOfCustomer = new HashSet<>();
        setOfPizza = new HashSet<>();
        queueOfOrders = new LinkedList<>();
        tableManager = new TableManager(NUMBER_OF_TABLES);
        orderPreparation = new OrderPreparation(setOfCooks,queueOfOrders);
        ingredientsMonitor = new IngredientsMonitor(setOfIngredient,mapOfMinQuantityOfIngredient);
        orderProcedure = new OrderProcedure(tableManager,ingredientsMonitor,orderPreparation);
        pizzeria = new Pizzeria(ingredientsMonitor,setOfCustomer,setOfPizza,orderProcedure);
        customer = new Customer("Pawel", CustomerType.STUDENT);
    }


    @Test
    @DisplayName("program orders ingredients properly")
    void createOrder(){
        //Given
        Ingredient cheese = new Ingredient("cheese",60);
        Ingredient salami = new Ingredient("salami",60);
        Ingredient corn = new Ingredient("corn",60);
        pizzeria.addIngredient(cheese,45);
        pizzeria.addIngredient(salami,45);
        pizzeria.addIngredient(corn,45);
        Map<Ingredient,Integer> ingredientNeededToMargheritta = new HashMap<>();
        ingredientNeededToMargheritta.put(cheese,5);
        ingredientNeededToMargheritta.put(salami,10);
        //When
        Pizza margherita = new Pizza(35,"margheritta", ingredientNeededToMargheritta);
        pizzeria.addPizza(margherita);
        Map<Pizza,Integer> mapOfOrderedPizzasWithQuantity = new HashMap<>();
        mapOfOrderedPizzasWithQuantity.put(margherita,3);
        pizzeria.createOrder(mapOfOrderedPizzasWithQuantity,false,customer);
        //Then
        assertEquals(145, salami.getQuantityInStock());
    }
    @Test
    @DisplayName("check if price is correct")
    void createOrderAndCheckPrice(){
        //Given
        Ingredient cheese = new Ingredient("cheese",60);
        Ingredient salami = new Ingredient("salami",60);
        pizzeria.addIngredient(salami,45);
        pizzeria.addIngredient(cheese,45);
        Map<Ingredient,Integer> ingredientNeededToMargheritta = new HashMap<>();
        ingredientNeededToMargheritta.put(cheese,5);
        ingredientNeededToMargheritta.put(salami,10);
        //When
        Pizza margherita = new Pizza(35,"margheritta", ingredientNeededToMargheritta);
        pizzeria.addPizza(margherita);
        Map<Pizza,Integer> mapOfOrderedPizzasWithQuantity = new HashMap<>();
        mapOfOrderedPizzasWithQuantity.put(margherita,3);
        Order order = new Order(mapOfOrderedPizzasWithQuantity,false,customer);
        //Then
        assertEquals(105,order.getFinalPrcie());
    }
}