package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* 
Генератор паролей
*/
//      Реализуй логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
//        Требования к паролю:
//        1) 8 символов.
//        2) только цифры и латинские буквы разного регистра.
//        3) обязательно должны присутствовать цифры, и буквы разного регистра.
//        Все сгенерированные пароли должны быть уникальные.
//
//        Пример правильного пароля:
//        wMh7smNu

//ASCII codes
//digits - 48-57
//big letter - 65-90
//small letter - 97-122
public class Solution {
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] array= new byte[8];
        boolean unique = false;
        String pass;
        boolean bigLetter;
        boolean smallLetter;
        boolean digit;

        while (!unique) {
            for (int i = 0; i < 8; i++) {
                array[i] = getRandomNumber();
            }
            pass = new String(array);

            bigLetter = false;
            smallLetter = false;
            digit = false;

            for (byte b : array) {
                if (b >= 48 && b <= 57)
                    digit = true;
                if (b >= 65 && b <= 90)
                    bigLetter = true;
                if (b >= 97 && b <= 122)
                    smallLetter = true;
            }

            if(digit!=true || bigLetter!=true || smallLetter!=true)
                continue;

            if (list.contains(pass))
                continue;
            else {
                list.add(pass);
                unique = true;
            }
        }

        try {
            result.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static byte getRandomNumber() {
        Random gen = new Random();
        byte number;

        number = (byte) (48 + gen.nextInt()*(122-48+1));

        if((number >= 48 && number <= 57) || (number >= 65 && number <= 90) || (number >= 97 && number <= 122))
            return number;
        else
            return getRandomNumber();
    }
}
