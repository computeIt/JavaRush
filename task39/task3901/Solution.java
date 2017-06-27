package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
Реализуй метод lengthOfLongestUniqueSubstring таким образом, чтобы он возвращал длину самой длинной подстроки без повторяющихся символов, найденной в строке полученной в качестве параметра.
Например, для строки «a123bcbccaa» — 6, а для строки «ttttwt» — 2.
Если анализируемая строка пуста или равна null — верни 0.

Требования:
1. Метод lengthOfLongestUniqueSubstring должен возвращать длину подстроки с максимальным количеством уникальных символов.
2. Метод lengthOfLongestUniqueSubstring должен возвращать 0 для пустой строки, или строки равной null.
3. Метод lengthOfLongestUniqueSubstring должен быть публичным.
4. Метод lengthOfLongestUniqueSubstring должен быть статическим.*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if(s == null || s.length()==0)
            return 0;

        Set<Character> set = new HashSet<>();
        char[] array = s.toCharArray();
        int result = 1;
        for(Character ch : array){
            if(!set.contains(ch)){      //еслив множестве нет текущего символа
                set.add(ch);            //добавляем его туда
            }
            else {                      //а если символ уже был среди всех рассмотренных
                if(set.size()>result)   //и при этом количество ранее перебранных символов больше текущей максимальной длины строки
                    result = set.size();//запоминаем длину максимальной неповторяющейся строки
                set.clear();            //очищаем множество
                set.add(ch);            //добавляем в множество текущий символ, даже если он встречался раньше, 
            }                           //потому что минимальное возвращаемое значение для непустой строки =1
        }

        return result;
    }
}
