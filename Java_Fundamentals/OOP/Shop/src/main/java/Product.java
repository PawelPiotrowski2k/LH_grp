import java.util.UUID;

public class Product {
    private final String name;
    private final String catergory;
    private double price;
    private final double tax;
    private final double discount;
    private final String id;


    public Product(String name, String catergory, double price, double discount) {
        this.name = name;
        this.catergory = catergory;
        this.price = price;
        this.tax = 1.23;
        this.id = UUID.randomUUID().toString();
        this.discount = discount;
    }

    public String getCatergory() {
        return catergory;
    }

    public String getName() {
        return name;
    }

    public double getFinalPrice() {
        return Math.round(this.price * this.discount * this.tax * 100.0) / 100.0;
    }
}
