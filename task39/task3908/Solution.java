package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
Реализуй метод isPalindromePermutation(String s) который будет возвращать true, если из всех символов строки s можно составить палиндром. Иначе — false.

Символы в анализируемой строке ограничены кодировкой ASCII.
Регистр букв не учитывается.

Требования:
1. Метод isPalindromePermutation должен возвращать true, если выполнив перестановку символов входящей строки можно получить палиндром.
2. Метод isPalindromePermutation должен возвращать false, если выполнив перестановку символов входящей строки получить палиндром невозможно.
3. Метод isPalindromePermutation должен быть публичным.
4. Метод isPalindromePermutation должен быть статическим.*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        System.out.println("палиндром возможен? " + isPalindromePermutation(s));

    }

    public static boolean isPalindromePermutation(String s) {
        if(s == null || s == "")
            return true;
        s = s.toLowerCase();

        char[] chars = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<chars.length; i++){
            if(map.containsKey(chars[i])){
                map.put(chars[i], map.get(chars[i])+1);
            }
            else{
                map.put(chars[i], 1);
            }
        }

        int counter = 0;

        for(int i : map.values()){
            if(i%2 != 0)
                counter++;
        }

        return counter > 1 ? false : true;
    }
}
