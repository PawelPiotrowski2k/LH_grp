package Models;

import java.util.List;
import java.util.Map;

public class Pizza {
    private final double price;
    private final String name;
    private final Map<Ingredients,Integer> ingredientsNeeded;

    public Pizza(double price, String name, Map<Ingredients, Integer> ingredientsNeeded) {
        this.price = price;
        this.name = name;
        this.ingredientsNeeded = ingredientsNeeded;
    }

    public double getPrice() {
        return price;
    }

    public Map<Ingredients, Integer> getIngredientsNeeded() {
        return ingredientsNeeded;
    }
}
