package Models;

public class Ingredients {
    private final int minQuantity;
    private final String name;
    private int quantityInStock;


    public Ingredients(int minQuantity, String name, int quantityInStock) {
        this.minQuantity = minQuantity;
        this.name = name;
        this.quantityInStock = quantityInStock;
    }
    public void orderIngredients(int value){
        quantityInStock += value;
    }
    public void useIngredients(int ammount){
        this.quantityInStock = quantityInStock- ammount;
    }


}
