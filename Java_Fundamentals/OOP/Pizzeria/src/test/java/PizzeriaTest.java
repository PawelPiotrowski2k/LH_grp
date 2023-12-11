import Customer.Customer;

import Order.Order;
import Order.OrderPreparation;
import Order.OrderProcedure;
import Table.TableManager;
import ingredient.Ingredient;
import ingredient.IngredientsMonitor;
import Pizza.Pizza;
import Cook.Cook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class PizzeriaTest {
    Pizzeria pizzeria;
    OrderProcedure orderProcedure;
    IngredientsMonitor ingredientsMonitor;
    OrderPreparation orderPreparation;

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
        tableManager = new TableManager(NUMBER_OF_TABLES);
        orderProcedure = new OrderProcedure(tableManager,ingredientsMonitor,orderPreparation);
        ingredientsMonitor = new IngredientsMonitor(setOfIngredient,mapOfMinQuantityOfIngredient);
        pizzeria = new Pizzeria(ingredientsMonitor,orderPreparation,setOfCustomer,setOfPizza,orderProcedure);
        orderPreparation = new OrderPreparation(setOfCooks,queueOfOrders);
        setOfIngredient = new HashSet<>();
        mapOfMinQuantityOfIngredient = new HashMap<>();
        setOfCooks = new HashSet<>();
        setOfCustomer = new HashSet<>();
        setOfPizza = new HashSet<>();
        queueOfOrders = new LinkedList<>();
    }
    @Test
    void createOrder(){

        

        pizzeria.createOrder();
    }


  
}