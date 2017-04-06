package com.javarush.task.task32.task3201;

/* 
Запись в существующий файл
*/
//      В метод main приходят три параметра:
//        1) fileName — путь к файлу;
//        2) number — число, позиция в файле;
//        3) text — текст.
//        Записать text в файл fileName начиная с позиции number.
//        Запись должна производиться поверх старых данных, содержащихся в файле.
//        Если файл слишком короткий, то записать в конец файла.
//        Используй RandomAccessFile и его методы seek и write

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) throws IOException {
        File file = new File(args[0]);
        long number = Integer.parseInt(args[1]);
        String text = "";

        for(int i=2; i<args.length; i++)
            text += args[i] + " ";

        text = text.trim();

        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        if(raf.length() < number){
            raf.setLength(raf.length() + text.length());
            raf.seek(raf.length());
        }
        else if(number + text.length() > raf.length()) {
            raf.setLength(number + text.length());
            raf.seek(number);
        }
        else
            raf.seek(number);

        raf.write(text.getBytes());

        raf.close();
    }
}
