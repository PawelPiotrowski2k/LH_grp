package com.company;

import java.util.ArrayList;
import java.util.List;

class NumberFinder {

    public static void main(String[] args) {
        ArrayList<SortedNumbers> numbersToSort = new ArrayList<SortedNumbers>();
        SortedNumbers numbers1 = new SortedNumbers();
        SortedNumbers numbers2 = new SortedNumbers();
        SortedNumbers numbers3 = new SortedNumbers();
        SortedNumbers numbers4 = new SortedNumbers();
        SortedNumbers numbers5 = new SortedNumbers();
        numbersToSort.add(numbers1);
        numbersToSort.add(numbers2);
        numbersToSort.add(numbers3);
        numbersToSort.add(numbers4);
        numbersToSort.add(numbers5);
        for (SortedNumbers numbers  :
                numbersToSort) {
            SortedNumbers.quickSort(numbers.getSortedNumbers(),0,numbers.getSortedNumbers().size()-1);
            for (int a :
                    numbers.getSortedNumbers()) {
                System.out.print(a + " ");
            }
            System.out.println(" lowest number is: " + numbers.getLowestNumber());
        }
    }
    public static class SortedNumbers {
        private int lowestNumber;
        private List<Integer> numbers;
        SortedNumbers(){
            this.numbers = new ArrayList<>();
            for (int i = 0; i <= getRandomNumber(7,15);i++) {
                numbers.add(getRandomNumber(10,100));
            }
        }
        public List<Integer> getSortedNumbers() {
            return numbers;
        }
        public int getLowestNumber() {
            lowestNumber = Integer.MAX_VALUE;
            for (int a :
                    numbers) {
                if(a < lowestNumber){
                    lowestNumber = a;
                }
            }
            return lowestNumber;
        }
        public static int getRandomNumber (int min, int max){
            return (int) (Math.random() * (max - min + 1) +10);
        }
        public static void quickSort(List numbers, int low, int high){
            if(low >= high){
                return;
            }
            int pivot = (int) numbers.get(high);
            int left = low;
            int right = high;
            while (left < right){
                while ((int) numbers.get(left) <= pivot && left < right){
                    left++;
                }
                while ((int) numbers.get(right) >= pivot && left < right){
                    right--;
                }
                swap(numbers,left,right);
            }
            swap(numbers,left,high);
            quickSort(numbers,low,left-1);
            quickSort(numbers,left + 1,high);
        }
        public static void swap(List numbers, int index1,int index2){
            int temp = (int) numbers.get(index1);
            numbers.set(index1,numbers.get(index2));
            numbers.set(index2,temp);
        }
    }
}