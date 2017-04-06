package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
//      Реализуй логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
//        Возвращаемый объект ни при каких условиях не должен быть null.
//        Метод main не участвует в тестировании.
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("C:\\Users\\Igor\\Desktop\\test.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            while (reader.ready()) {
                String s = reader.readLine();
                writer.write(s);
            }
        }catch(Exception e){
            return writer;//Возвращаемый объект ни при каких условиях не должен быть null.
        }
        return writer;
    }
}
