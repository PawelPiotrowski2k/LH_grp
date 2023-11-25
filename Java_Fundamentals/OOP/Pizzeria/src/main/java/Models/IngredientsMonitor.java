package Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IngredientsMonitor {
    private final Set<Ingredients> listOfIngredients;

    public IngredientsMonitor(Set<Ingredients> listOfIngredients) {
        this.listOfIngredients = listOfIngredients;
    }
    public void checkIngredients(Set<Ingredients> ingredients) {
        for (Ingredients ingredient :
                ingredients) {
            if(ingredient.getMinQuantity() > ingredient.getQuantityInStock()){
                int numberOfIngredientToOrder =  ingredient.getMinQuantity() - ingredient.getQuantityInStock() + 10;
                ingredient.orderIngredients(numberOfIngredientToOrder);
            }
        }
    }
    public Map<String, Integer> getMapOfIngredientsFromPizzaList(List<Pizza> listOfPizza){
        Map<String, Integer> mapOfIngredients = new HashMap<>();
        for (Pizza pizza :
                listOfPizza) {
            pizza.getIngredientsNeeded();

        }
    }
}
