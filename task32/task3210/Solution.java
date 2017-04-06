package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/
//      В метод main приходят три параметра:
//        1) fileName — путь к файлу;
//        2) number — число, позиция в файле;
//        3) text — текст.
//
//        Считать текст с файла начиная с позиции number, длинной такой же как и длинна переданного текста в третьем параметре.
//        Если считанный текст такой же как и text, то записать в конец файла строку ‘true‘, иначе записать ‘false‘.
//        Используй RandomAccessFile и его методы seek(long pos), read(byte b[], int off, int len), write(byte b[]).
//        Используй convertByteToString(byte readBytes[]) для конвертации считанной строчки в текст.

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");

        int number = Integer.parseInt(args[1]);
        String text = "";

        for(int i=2; i<args.length; i++){
            text += args[i] + " ";
        }

        text = text.substring(0, text.length()-1);

        byte[] byteArray = new byte[text.getBytes().length];
        raf.seek(number);
        raf.read(byteArray, 0, text.length());
        String str = convertByteToString(byteArray);

        raf.seek(raf.length());

        if(str.equals(text))
            raf.write("true".getBytes());
        else if(!str.equals(text))
            raf.write("false".getBytes());

        raf.close();
    }

    private static String convertByteToString(byte[] byteArray) {
        return new String(byteArray);
    }
}
