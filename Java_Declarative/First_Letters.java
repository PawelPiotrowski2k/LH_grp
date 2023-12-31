package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class Main {

    public static void main(String[] args) {
        List<String> words = new ArrayList<String>();
        words.add("java");
        words.add("programing");
        words.add("language");
        for (String a :
                changeFirstLetter(words)) {
            System.out.println(a);
        }
    }

    public static List<String> changeFirstLetter(List<String> words) {
        int indexOfWord = 0;
        for (String word :words) {
            indexOfWord = words.indexOf(word);
            word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            words.set(indexOfWord, word);
        }
        return words;
    }
}