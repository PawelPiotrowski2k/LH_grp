package Models;

import java.util.List;
import java.util.Map;

public class Pizza {
    private final double price;
    private final String name;
    private final Map<String,Integer> ingredientsNeeded;

    public Pizza(double price, String name, Map<String, Integer> ingredientsNeeded) {
        this.price = price;
        this.name = name;
        this.ingredientsNeeded = ingredientsNeeded;
    }

    public double getPrice() {
        return price;
    }

    public Map<String, Integer> getIngredientsNeeded() {
        return ingredientsNeeded;
    }
}
