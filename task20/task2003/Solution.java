package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/

public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.fillInPropertiesMap();
    }
    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        FileInputStream inputStream = new FileInputStream(name);
        load(inputStream);
        OutputStream outputStream = new FileOutputStream(name);
        save(outputStream);
        inputStream.close();
        outputStream.close();
        reader.close();
    }
    public void save(OutputStream outputStream) throws Exception {
        Properties props = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet()) props.setProperty(entry.getKey(), entry.getValue());    //наполняем массив Properties парами ключ-значение
        props.store(outputStream, null);    //выгружаем пары массива Properties в файл .properties
    }
    public void load(InputStream inputStream) throws Exception {
        Properties props = new Properties();
        props.load(inputStream);    //выгружаем Properties из файла .properties в массив
        for (Map.Entry<Object, Object> entry : props.entrySet()) properties.put(entry.getKey().toString(), entry.getValue().toString());    //заполняем статический массив из массива Properties
    }
}
