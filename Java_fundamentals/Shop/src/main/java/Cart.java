

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart {

    private ArrayList<Product> listOfProducts = new ArrayList<Product>();
    private final double discount;
    DecimalFormat df = new DecimalFormat("#.##");

    public Cart(String nameDiscount) {
        Discount discount1 = Discount.valueOf(nameDiscount);
        this.discount = discount1.getDiscountValue();
    }
    public double finalPrice(){

        double finalPrice = 0;
        for (Product product :
                listOfProducts) {
            finalPrice = finalPrice + product.finalPrice();
        }
        finalPrice = Math.round(finalPrice * this.discount * 100.0) / 100.0;
        return finalPrice;
    }
    public double getDiscount(){
        return discount;
    }
    public void addProduct (Product product){
        listOfProducts.add(product);
    }
    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }
}
