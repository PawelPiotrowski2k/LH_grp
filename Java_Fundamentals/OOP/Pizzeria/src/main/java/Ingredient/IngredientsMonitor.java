package Ingredient;

import Pizza.Pizza;

import java.util.*;

public class IngredientsMonitor{
    private final Set<Ingredient> setOfIngredients;
    private final Map<Ingredient,Integer> mapOfMinQuantityOfIngredients;
    private static int QUANTITY_TO_ORDER = 100;

    public IngredientsMonitor(Set<Ingredient> setOfIngredients, Map<Ingredient,Integer> mapOfMinQuantityOfIngredients) {
        this.setOfIngredients = setOfIngredients;
        this.mapOfMinQuantityOfIngredients = mapOfMinQuantityOfIngredients;
    }

    public void addIngredient(Ingredient ingredient, int minQuantity) {
        setOfIngredients.add(ingredient);
        mapOfMinQuantityOfIngredients.put(ingredient, minQuantity);
    }

    public void checkIngredients() {
        for (Ingredient ingredient :
                setOfIngredients) {
            int numberOfIngredientToOrder = 0;
            if (mapOfMinQuantityOfIngredients.get(ingredient) > ingredient.getQuantityInStock()) {
                numberOfIngredientToOrder = mapOfMinQuantityOfIngredients.get(ingredient) - ingredient.getQuantityInStock()  + QUANTITY_TO_ORDER;
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
            int value  = ingredientEntry.getValue();
            if (ingredient.getQuantityInStock() < value) {
                return false;
            }
        }
        return true;
    }

    private Map<Ingredient, Integer> mapIngredientsUsageFromOrder(Map<Pizza, Integer> mapOfPizzaWithQuantity) {
        Map<Ingredient, Integer> resultMap = new HashMap<>();
        for (Map.Entry<Pizza, Integer> listOfPizzasEntry : mapOfPizzaWithQuantity.entrySet()) {
            Map<Ingredient, Integer> pizzaIngredients = new HashMap<>(listOfPizzasEntry.getKey().ingredientsNeeded());
            for (Map.Entry<Ingredient, Integer> ingredientEntry : pizzaIngredients.entrySet()) {
                Ingredient ingredient = ingredientEntry.getKey();
                int quantity = ingredientEntry.getValue() * listOfPizzasEntry.getValue();
                resultMap.compute(ingredient,(key, value) -> value != null ? value + quantity : quantity);
            }
        }
        return resultMap;
    }


//private Map<Ingredient, Integer> mapIngredientsUsageFromOrder(Map<Pizza, Integer> mapOfPizzaWithQuantity) {
//    return mapOfPizzaWithQuantity.entrySet().stream()
//            .flatMap(entry -> {
//                Pizza pizza = entry.getKey();
//                int quantity = entry.getValue();
//                return pizza.getIngredientsNeeded().entrySet().stream()
//                        .map(ingredientEntry -> {
//                            Ingredient ingredient = ingredientEntry.getKey();
//                            int ingredientQuantity = ingredientEntry.getValue() * quantity;
//                            return new AbstractMap.SimpleEntry<>(ingredient, ingredientQuantity);
//                        });
//            })
//            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
//}



}
