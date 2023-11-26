package Models;

import java.util.List;
import java.util.Map;

public class Pizza {
    private final int price;
    private final String name;
    private final Map<String,Integer> ingredientsNeeded;

    public Pizza(int price, String name, Map<String, Integer> ingredientsNeeded) {
        this.price = price;
        this.name = name;
        this.ingredientsNeeded = ingredientsNeeded;
    }

    public int getPrice() {
        return price;
    }

    public Map<String, Integer> getIngredientsNeeded() {
        return ingredientsNeeded;
    }
}
