package com.javarush.task.task39.task3909;

/* 
Одно изменение
Реализуй метод isOneEditAway(String first, String second) который будет возвращать true, если возможно изменить/добавить/удалить один символ в одной из строк и получить другую.

Символы в анализируемой строке ограничены кодировкой ASCII.
Регистр символов учитывается.

Требования:
1. Метод isOneEditAway должен корректно работать для строк одинаковой длины.
2. Метод isOneEditAway должен корректно работать для строк разной длины.
3. Метод isOneEditAway должен корректно работать для пустых строк.
4. Метод isOneEditAway должен быть публичным.*/
public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isOneEditAway(String first, String second) {
        int f = first.length()-second.length();
        if(f>1 || f<-1)         //разница длин 2 символа и больше
            return false;

        if(first == "" && second == "")     //обе пустые
            return true;

        if(first.equals(second))        //идентичные
            return true;

        StringBuffer shorterString = new StringBuffer(second);
        StringBuffer longerString = new StringBuffer(first);

        if(first.length() >= second.length()){
            shorterString = new StringBuffer(second);
            longerString = new StringBuffer(first);
        }
        else if(first.length() < second.length()){
            shorterString = new StringBuffer(first);
            longerString = new StringBuffer(second);
        }

        for(int i=0; i<shorterString.length(); i++){    //бежим по короткой строке
            char curChar = shorterString.charAt(i);
            int position = longerString.indexOf(String.valueOf(curChar));   //ищем в более длинной строке символы из короткой
            if(position != -1)
                longerString.deleteCharAt(position);    //и удаляем их из длинной строки, если они есть
        }

        if(longerString.length() <= 1)      //если после удаления всех одинаковых символов, осталось не более 1
            return true;                    //то условие выполняется

        return false;
    }
}
