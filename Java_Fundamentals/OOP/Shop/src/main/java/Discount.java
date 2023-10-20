import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return Arrays.stream(Discount.values()).collect(Collectors.toList());
    }
}
