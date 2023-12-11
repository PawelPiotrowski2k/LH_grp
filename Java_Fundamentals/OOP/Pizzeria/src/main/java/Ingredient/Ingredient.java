package Ingredient;

import java.util.UUID;
public class Ingredient {

    private final String name;
    private int quantityInStock;
    private final String id;


    public Ingredient( String name, int quantityInStock) {
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.id = UUID.randomUUID().toString();
    }

    public void orderIngredients(int value){
        quantityInStock =  quantityInStock + value;
    }

    public void useIngredients(int ammount){
        this.quantityInStock = quantityInStock - ammount;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public String getId() {
        return id;
    }
}
