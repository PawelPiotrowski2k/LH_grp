package Pizza;

import Ingredient.Ingredient;


import java.util.Map;

public class Pizza {
    private final double price;
    private final String name;
    private final Map<Ingredient,Integer> ingredientsNeeded;

    public Pizza(double price, String name, Map<Ingredient, Integer> ingredientsNeeded) {
        this.price = price;
        this.name = name;
        this.ingredientsNeeded = ingredientsNeeded;
    }

    public double getPrice() {
        return price;
    }

    public Map<Ingredient, Integer> getIngredientsNeeded() {
        return ingredientsNeeded;
    }
}
