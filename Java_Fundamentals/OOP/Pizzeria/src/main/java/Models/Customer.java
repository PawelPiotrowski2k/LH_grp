package Models;

import java.util.UUID;

public class Customer {
    private final String name;
    private final CustomerType customerType;
    private final String id;

    public Customer(String name, CustomerType customerType) {
        this.name = name;
        this.customerType = customerType;
        this.id = UUID.randomUUID().toString();
    }
    public void eating(){
        System.out.println(name + " nom nom");
    }

    public CustomerType getCustomerType() {
        return customerType;
    }
}
