import java.util.ArrayList;
import java.util.List;

public enum Discount {
    PIATUNIO(0.9),
    HURTOWA(0.7),
    SWIATECZNA(0.8);

    Double discountValue;

    Discount(Double value) {
        this.discountValue = value;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public static List<Discount> getListOfDiscount() {
        List<Discount> discounts = new ArrayList<Discount>();
        for (Discount discount :
                Discount.values()) {
            discounts.add(discount);
        }
        return discounts;
    }
}
