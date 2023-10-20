import java.util.UUID;

public class Product {
    private final String name;
    private final String catergory;
    private double price;
    private final double tax;
    private final double discount;
    private final String id;


    Product(String name, String catergory, double price, double tax, double discount) {
        this.name = name;
        this.catergory = catergory;
        this.price = price;
        this.tax = tax;
        this.discount = discount;
        this.id = UUID.randomUUID().toString();
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
class ProductBuilder {
    private String name;
    private String catergory;
    private double price;
    private double tax;
    private double discount;

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setCatergory(String catergory) {
        this.catergory = catergory;
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder setTax(double tax) {
        this.tax = tax;
        return this;
    }

    public ProductBuilder setDiscount(double discount) {
        this.discount = discount;
        return this;
    }


    public Product build() {
        return new Product(name, catergory, price, tax, discount);
    }
}
