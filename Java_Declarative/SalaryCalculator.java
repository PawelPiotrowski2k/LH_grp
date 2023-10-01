package com.company;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Double> taxMap = new HashMap<>();
        BigDecimal salary = new BigDecimal(27000);
        taxMap.put("Poland", 0.6);
        taxMap.put("Germany", 0.7);
        taxMap.put("USA", 0.5);
        taxMap.put("France", 0.4);
        System.out.println(calcSalary(salary,taxMap,"Poland"));
        System.out.println(calcSalary(salary,taxMap,"Germany"));
        System.out.println(calcSalary(salary,taxMap,"USA"));
        System.out.println(calcSalary(salary,taxMap,"France"));
    }

    public static BigDecimal calcSalary(BigDecimal salary, Map<String, Double> taxes, String country) {
        Double tax = taxes.get(country);
        BigDecimal salaryNet = salary.multiply(BigDecimal.valueOf(tax));
        return salaryNet;
    }
}

