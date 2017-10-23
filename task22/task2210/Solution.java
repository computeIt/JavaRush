package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.

Пример
getTokens("level22.lesson13.task01", ".")

Возвращает
{"level22", "lesson13", "task01"}

Требования:
1. Метод getTokens должен использовать StringTokenizer.
2. Метод getTokens должен быть публичным.
3. Метод getTokens должен принимать два параметра типа String.
4. Массив типа String возвращенный методом getTokens должен быть заполнен правильно(согласно условию задачи).*/
public class Solution {
    public static void main(String[] args) {
        String[] temp = getTokens("level22.lesson13.task01", ".");
        for(String s : temp)
            System.out.println(s);
    }

    public static String[] getTokens(String query, String delimiter) {
//        String[] result = query.split(delimiter);
//        return result;

        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);
        String[] result = new String[stringTokenizer.countTokens()];//countTokens() дает нам будущее кол-во токенов 
        int i=0;
        while(stringTokenizer.hasMoreTokens()){
            result[i++] = stringTokenizer.nextToken();//заполняем массив токенами, полученными после разбиения через делимитер
        }
        return result;
    }
}
