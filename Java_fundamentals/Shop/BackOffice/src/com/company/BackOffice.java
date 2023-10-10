package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class BackOffice {


    public static void main(String[] args) {
        ArrayList<Cart> finishedOrder = new ArrayList<Cart>();
        ArrayList<Product> allProducts = new ArrayList<Product>();
        boolean endshoping = false;
        boolean quit = false;
        int cartid = 0;
        Product mleko = new Product("mleko", "nabiał",7.99,1,0.9);
        Product ser = new Product("ser", "nabiał",4.67,2,1);
        Product cola = new Product("cola", "napój",5,3,1);
        Product chleb = new Product("chleb", "pieczywo",3,4,0.8);
        allProducts.add(mleko);
        allProducts.add(ser);
        allProducts.add(cola);
        allProducts.add(chleb);

        Scanner scanner = new Scanner(System.in);
        while (!quit){
            menu();
            switch (scanner.nextInt()){
                //nowe zamówienie
                case 1:
                    Cart cart = new Cart(cartid);
                    cartid++;
                    endshoping = false;
                    cartMenu();
                    while (!endshoping) {
                        int choice = scanner.nextInt();
                        switch (choice) {
                            //dodaj zamówienie
                            case 1:
                                System.out.println("wprowadź index produktu");
                                int indexOfProduct = scanner.nextInt();
                                if (indexOfProduct >= 1 && indexOfProduct <= allProducts.size()) {
                                    cart.addProduct(allProducts.get(indexOfProduct - 1));
                                    System.out.println("pomyślnie dodano " + allProducts.get(indexOfProduct - 1).getName());
                                } else {
                                    System.out.println("podałeś zły index " + '"' + indexOfProduct + '"');
                                }
                                cartMenu();
                                //kończenie koszyka
                                break;
                            case 2:
                                finishedOrder.add(cart);
                                endshoping = true;
                                break;
                            default:
                                System.out.println("podane złe dane");
                                break;
                        }
                    }
                    break;
                //lista produktów
                case 2:
                    listOfProducts(allProducts);
                    break;
                //lista przecen
                case 3:
                    listOfDiscount(allProducts);
                    break;
                //skończone koszyki
                case 4:
                    finishedCarts(finishedOrder);
                    break;
                //wyjście
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("podałeś złe dane");
                    break;
            }
        }
    }
    public static void menu(){
    System.out.println("Wybierz opcje:");
    System.out.println("1: złóż zamówienie");
    System.out.println("2: Lista produktów");
    System.out.println("3: wszystkie przeceny");
    System.out.println("4: złożone zamówienia");
    System.out.println("5: wyjdź");
    }
    public static void cartMenu(){
    System.out.println("Wybierz opcje:");
    System.out.println("1: dodaj produkt do zamówienia");
    System.out.println("2: skończ zamówienie");
    }
public static void listOfProducts(ArrayList<Product> products){
        int index = 1;
    System.out.println("lista dostępnych produktów: ");
    for (Product product :
            products) {
    System.out.println(index + " " + product.getName() + " " + product.getCatergory());
        index++;
    }
    System.out.println();
}
public static void listOfDiscount(ArrayList<Product> products){
    int index = 1;
    System.out.println("lista dostępnych produktów: ");
    for (Product product :
            products) {
        System.out.println(index + " " + product.getName() + " " + product.getDiscount());
        index++;
    }
}
public static void finishedCarts(ArrayList<Cart> finishedOrder){
        int cartIndex = 1;
    for (Cart orders :
            finishedOrder) {
        System.out.print(cartIndex + ". ");
        for (Product product :
                orders.getListOfProducts()) {
            System.out.print(product.getName() + " ");
        }
        cartIndex++;
        System.out.println();
    }
}
}
