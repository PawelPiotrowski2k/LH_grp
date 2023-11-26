package Models;

import java.util.*;

public class IngredientsMonitor {

    public IngredientsMonitor() {

    }

    public void checkIngredients(Set<Ingredients> ingredients) {
        for (Ingredients ingredient :
                ingredients) {
            if (ingredient.getMinQuantity() > ingredient.getQuantityInStock()) {
                int numberOfIngredientToOrder = ingredient.getMinQuantity() - ingredient.getQuantityInStock() + 100;
                ingredient.orderIngredients(numberOfIngredientToOrder);
            }
        }
    }
    public void subIngredientUsedInOrder(Order order, Set<Ingredients> setOfIngredients) {
        Map<String, Integer> mapOfUsedIngredients = new HashMap<>(mapIngredientsUsageFromOrder(order));
        List<Ingredients> listOfIngredients = new ArrayList<>(setOfIngredients);
        for (Map.Entry<String, Integer> ingredientEntry :
                mapOfUsedIngredients.entrySet()) {
            String ingredientId = ingredientEntry.getKey();
            Integer value = ingredientEntry.getValue();
            Ingredients ingredient = getIngredientFromId(ingredientId, listOfIngredients);
            ingredient.useIngredients(value);
        }
    }

    public boolean checkIfThereIsEnoughIngredients(Order order, Set<Ingredients> setOfIngredients) {
        Map<String, Integer> mapOfUsedIngredients = mapIngredientsUsageFromOrder(order);
        List<Ingredients> listOfIngredients = new ArrayList<>(setOfIngredients);
        for (Map.Entry<String, Integer> ingredientEntry :
                mapOfUsedIngredients.entrySet()) {
            String ingredientId = ingredientEntry.getKey();
            Integer value = ingredientEntry.getValue();
            Ingredients ingredient = getIngredientFromId(ingredientId, listOfIngredients);
            if (ingredient.getQuantityInStock() < value) {
                return false;
            }
        }
        return true;
    }

    private Ingredients getIngredientFromId(String ingredientId, List<Ingredients> listOfIngredients) {
        for (Ingredients ingredient :
                listOfIngredients) {
            if (ingredient.getId().equals(ingredientId)) {
                return ingredient;
            }
        }
        return null;
    }

    private Map<String, Integer> mapIngredientsUsageFromOrder(Order order) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (Pizza pizza : order.getListOfPizzas()) {
            Map<String, Integer> pizzaIngredients = pizza.getIngredientsNeeded();
            for (Map.Entry<String, Integer> ingredientEntry : pizzaIngredients.entrySet()) {
                String ingredientId = ingredientEntry.getKey();
                int quantity = ingredientEntry.getValue();
                resultMap.merge(ingredientId, quantity, Integer::sum);
            }
        }
        return resultMap;
    }

}
