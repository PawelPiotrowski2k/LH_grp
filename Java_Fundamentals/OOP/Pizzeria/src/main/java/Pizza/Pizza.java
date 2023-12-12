package Pizza;

import Ingredient.Ingredient;

import java.util.Map;

public record Pizza(double price, String name, Map<Ingredient, Integer> ingredientsNeeded) {
}
