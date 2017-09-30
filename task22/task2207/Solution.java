package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Использовать StringBuilder.

Пример содержимого файла
рот тор торт о
о тот тот тот

Вывод:
рот тор
о о
тот тот

Требования:
1. Метод main должен считывать имя файла с клавиатуры.
2. В методе main должен быть использован StringBuilder
3. Список result должен быть заполнен корректными парами согласно условию задачи.
4. В классе Solution должен содержаться вложенный класс Pair.
5. В классе Pair должен быть объявлен конструктор без параметров (или конструктор по умолчанию).*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        String str;
        List<String> list = new ArrayList<>();
        try(
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream fis = new FileInputStream(reader.readLine());
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(fis))) {
            while((str = reader1.readLine()) != null){
                String[] strArr = str.split(" ");
                for(String s : strArr)
                    list.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0; i<list.size(); i++){
            for(int j=i+1; j< list.size(); j++){
                String sec = new StringBuilder(list.get(j)).reverse().toString();
                if(list.get(i).equals(sec) && !result.contains(new Pair(list.get(i), list.get(j)))){
                    result.add(new Pair(list.get(i), list.get(j)));
                }
            }
        }

        for(Pair pair : result)
            System.out.println(pair);
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;
        }
    }
}
