package ingredients;

import java.util.UUID;
//Ingredient!
public class Ingredients  {
    private final int minQuantity; //powinno być w ingredientsMonitor
    private final String name;
    private int quantityInStock;
    private final String id;


    public Ingredients(int minQuantity, String name, int quantityInStock) {
        this.minQuantity = minQuantity;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.id = UUID.randomUUID().toString();
    }
    public void orderIngredients(int value){
        quantityInStock += value;
    }
    public void useIngredients(int ammount){
        this.quantityInStock -= ammount;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public String getId() {
        return id;
    }
}
