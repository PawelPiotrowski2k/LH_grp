package ingredient;

import Pizza.Pizza;

import java.util.*;
import java.util.stream.Collectors;

public class IngredientsMonitor {
    private final Set<Ingredient> setOfIngredients;
    private final Map<Ingredient,Integer> mapOfMinQuantityOfIngredients;

    public IngredientsMonitor(final Set<Ingredient> setOfIngredients, Map<Ingredient,Integer> mapOfMinQuantityOfIngredients) {
        this.setOfIngredients = setOfIngredients;
        this.mapOfMinQuantityOfIngredients = mapOfMinQuantityOfIngredients;
    }

    public void addIngredient(Ingredient ingredients, int minQuantity) {
        if(setOfIngredients.add(ingredients)) {
            mapOfMinQuantityOfIngredients.put(ingredients, minQuantity);
        }
    }

    public void checkIngredients() {
        for (Ingredient ingredient :
                setOfIngredients) {
            if (mapOfMinQuantityOfIngredients.get(ingredient) > ingredient.getQuantityInStock()) {
                int numberOfIngredientToOrder = mapOfMinQuantityOfIngredients.get(ingredient) - ingredient.getQuantityInStock() + 100;//magic number
                ingredient.orderIngredients(numberOfIngredientToOrder);
            }
        }
    }

    public void subIngredientUsedInOrder(Map<Pizza, Integer> mapOfPizzaWithQuantity) {
        Map<Ingredient, Integer> mapOfUsedIngredients = new HashMap<>(mapIngredientsUsageFromOrder(mapOfPizzaWithQuantity));
        for (Map.Entry<Ingredient, Integer> ingredientEntry :
                mapOfUsedIngredients.entrySet()) {
            Ingredient ingredient = ingredientEntry.getKey();
            Integer value = ingredientEntry.getValue();

            ingredient.useIngredients(value);
        }
    }

    /**
     * @FunctionalInteface
     * x(Map<Pizza, Integer> mapOfPizzaWithQuantity, Callback<Void> callback)
     */

    public boolean checkIfThereIsEnoughIngredients(Map<Pizza, Integer> mapOfPizzaWithQuantity) {
        Map<Ingredient, Integer> mapOfUsedIngredients = new HashMap<>(mapIngredientsUsageFromOrder(mapOfPizzaWithQuantity));
        for (Map.Entry<Ingredient, Integer> ingredientEntry :
                mapOfUsedIngredients.entrySet()) {
            Ingredient ingredient = ingredientEntry.getKey();
            Integer value = ingredientEntry.getValue();
            if (ingredient.getQuantityInStock() < value) {
                return false;
            }
        }
        return true;
    }

//    private Map<Ingredient, Integer> mapIngredientsUsageFromOrder(Map<Pizza, Integer> mapOfPizzaWithQuantity) {
//        Map<Ingredient, Integer> resultMap = new HashMap<>();
//        //zobaczyć czy w tym forze powinien być for
//        for (Map.Entry<Pizza, Integer> listOfPizzasEntry : mapOfPizzaWithQuantity.entrySet()) {
//            Map<Ingredient, Integer> pizzaIngredients = new HashMap<>(listOfPizzasEntry.getKey().getIngredientsNeeded());
//            for (Map.Entry<Ingredient, Integer> ingredientEntry : pizzaIngredients.entrySet()) {
//                Ingredient ingredient = ingredientEntry.getKey();
//                int quantity = ingredientEntry.getValue() * listOfPizzasEntry.getValue();
//                resultMap.merge(ingredient, quantity, Integer::sum);
//            }
//        }
//        return resultMap;
//    }
private Map<Ingredient, Integer> mapIngredientsUsageFromOrder(Map<Pizza, Integer> mapOfPizzaWithQuantity) {
    return mapOfPizzaWithQuantity.entrySet().stream()
            .flatMap(entry -> {
                Pizza pizza = entry.getKey();
                int quantity = entry.getValue();
                return pizza.getIngredientsNeeded().entrySet().stream()
                        .map(ingredientEntry -> {
                            Ingredient ingredient = ingredientEntry.getKey();
                            int ingredientQuantity = ingredientEntry.getValue() * quantity;
                            return new AbstractMap.SimpleEntry<>(ingredient, ingredientQuantity);
                        });
            })
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
}



}
