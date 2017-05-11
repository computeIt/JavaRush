package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Serializable Solution
Сериализуйте класс Solution.
Подумайте, какие поля не нужно сериализовать, пометить ненужные поля — transient.
Объект всегда должен содержать актуальные итоговые данные.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать файл, открыть поток на чтение (input stream) и на запись(output stream)
2) создать экземпляр класса Solution - savedObject
3) записать в поток на запись savedObject (убедитесь, что они там действительно есть)
4) создать другой экземпляр класса Solution с другим параметром
5) загрузить из потока на чтение объект - loadedObject
6) проверить, что savedObject.string равна loadedObject.string
7) обработать исключения
*/

public class Solution implements Serializable {
    private static final long serialVersionUID = -6356653558390364307L;
    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("20-07-04.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Solution savedObject = new Solution(18);
        try {
            assert oos != null;
            oos.writeObject(savedObject);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("20-07-04.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Solution loadedObject = null;
        try {
            loadedObject = (Solution) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(savedObject.toString());
        System.out.println(loadedObject.toString());
        System.out.println(savedObject.string.equals(loadedObject.string));
    }
    private transient final String pattern = "dd MMMM yyyy, EEEE";    //это важно!!!
    private transient Date currentDate;
    private transient int temperature;
    String string;
    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;
        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }
    @Override
    public String toString() {
        return this.string;
    }
}
