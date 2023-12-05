package ingredients;

import Models.Pizza;

import java.util.*;

public class IngredientsMonitor {
    private final Set<Ingredients> setOfIngredients;

    public IngredientsMonitor(final Set<Ingredients> setOfIngredients) {
        this.setOfIngredients = setOfIngredients;
    }

    public void addIngredient(Ingredients ingredients) {
        setOfIngredients.add(ingredients);
    }

    public void checkIngredients() {
        for (Ingredients ingredient :
                setOfIngredients) {
            if (ingredient.getMinQuantity() > ingredient.getQuantityInStock()) {
                int numberOfIngredientToOrder = ingredient.getMinQuantity() - ingredient.getQuantityInStock() + 100;//magic number
                ingredient.orderIngredients(numberOfIngredientToOrder);
            }
        }
    }

    public void subIngredientUsedInOrder(Map<Pizza, Integer> mapOfPizzaWithQuantity) {
        Map<Ingredients, Integer> mapOfUsedIngredients = new HashMap<>(mapIngredientsUsageFromOrder(mapOfPizzaWithQuantity));
        for (Map.Entry<Ingredients, Integer> ingredientEntry :
                mapOfUsedIngredients.entrySet()) {
            Ingredients ingredient = ingredientEntry.getKey();
            Integer value = ingredientEntry.getValue();

            ingredient.useIngredients(value);
        }
    }

    /**
     * @FunctionalInteface
     * x(Map<Pizza, Integer> mapOfPizzaWithQuantity, Callback<Void> callback)
     */

    public boolean checkIfThereIsEnoughIngredients(Map<Pizza, Integer> mapOfPizzaWithQuantity) {
        Map<Ingredients, Integer> mapOfUsedIngredients = new HashMap<>(mapIngredientsUsageFromOrder(mapOfPizzaWithQuantity));
        for (Map.Entry<Ingredients, Integer> ingredientEntry :
                mapOfUsedIngredients.entrySet()) {
            Ingredients ingredient = ingredientEntry.getKey();
            Integer value = ingredientEntry.getValue();
            if (ingredient.getQuantityInStock() < value) {
                return false;
            }
        }
        return true;
    }

    private Map<Ingredients, Integer> mapIngredientsUsageFromOrder(Map<Pizza, Integer> mapOfPizzaWithQuantity) {
        Map<Ingredients, Integer> resultMap = new HashMap<>();
        //zobaczyć czy w tym forze powinien być for
        for (Map.Entry<Pizza, Integer> listOfPizzasEntry : mapOfPizzaWithQuantity.entrySet()) {
            Map<Ingredients, Integer> pizzaIngredients = new HashMap<>(listOfPizzasEntry.getKey().getIngredientsNeeded());
            for (Map.Entry<Ingredients, Integer> ingredientEntry : pizzaIngredients.entrySet()) {
                Ingredients ingredient = ingredientEntry.getKey();
                int quantity = ingredientEntry.getValue() * listOfPizzasEntry.getValue();
                resultMap.merge(ingredient, quantity, Integer::sum);
            }
        }
        return resultMap;
    }


}