package Customer;

public enum CustomerType {
    REGULAR (1),
    CHILD (0.9),
    STUDENT (0.6);

    CustomerType(double discountRate) {
    }
    private double discountRate;
    public double getValue(){
        return discountRate;
    }
}
