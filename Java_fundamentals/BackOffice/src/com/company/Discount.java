package com.company;

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
}
