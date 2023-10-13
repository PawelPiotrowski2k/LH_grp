
import java.util.ArrayList;

public class Product {
    private final String name;
    private final String catergory;
    private double price;
    private final double tax;
    private final double discount;
    private final int id;
    private static int lastCartId = 0;


    public Product(String name, String catergory, double price, double discount) {
        this.name = name;
        this.catergory = catergory;
        this.price = price;
        this.tax = 1.23;
        this.id = ++lastCartId;
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public String getCatergory(){
        return catergory;
    }

    public String getName(){
        return name;
    }

    public double finalPrice(){
        return this.price * this.discount * this.tax;
    }
}
