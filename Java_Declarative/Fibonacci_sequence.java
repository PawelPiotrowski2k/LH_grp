package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> firstNumbers = new ArrayList<Integer>();
        firstNumbers.add(Integer.parseInt(args[0]));
        firstNumbers.add(Integer.parseInt(args[1]));
        firstNumbers.add(Integer.parseInt(args[2]));
        int[] result = sequence(firstNumbers,Integer.parseInt(args[3]));
        for (int a : result) {
            System.out.println(a);
        }
    }
    public static int[] sequence(List<Integer> firstNumbers, int size){
        int[] result = new int[size];
        int firstnumber = firstNumbers.get(1);
        int secondnumber = firstNumbers.get(2);
        int helper = 1;
        for (int i = 0; i < size; i++) {
            helper = firstnumber + secondnumber;
            result[i] = helper;
            firstnumber = secondnumber;
            secondnumber = helper;
        }
        return result;
    }
}