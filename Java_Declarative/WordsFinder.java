package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String x = "Pawel Makary Wiktoria Kondziu Kondziu Kondziu Pawel";
        System.out.println(wordsFinder(x));

    }

    public static Map<String, Integer> wordsFinder(String text) {
        HashMap<String, Integer> words = new HashMap<>();
        String [] wordTable= text.split(" ");
        for (String word : wordTable) {
            if(words.containsKey(word)){
                words.replace(word,words.get(word)+1);
            }else{
                words.put(word,1);
            }
        }
        return  words;
    }
}
