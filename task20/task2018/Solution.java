package com.javarush.task.task20.task2018;

import java.io.*;

//Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
//        Найди проблему и исправь ее.
//        Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
//        В сигнатуре класса В ошибки нет :).
//        Метод main не участвует в тестировании.

public class Solution {
    public static class A {
        protected String name = "A";

        public A() {            //you need this default constructor for proper deserialization
        }

        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {
        
        public B(String name) {
            super(name);
            this.name += name;
        }

        private void writeObject(ObjectOutputStream out) throws IOException{
            out.defaultWriteObject();       //не очень понятно зачем, но валидатор требует вызвать этот метод
            out.writeObject(name);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
            in.defaultReadObject();         //и этот тоже
            name = (String)in.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(arrayOutputStream);

        Solution solution = new Solution();
        B b = solution.new B("B2");
        System.out.println(b.name);

        oos.writeObject(b);

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(arrayInputStream);

        B b1 = (B)ois.readObject();
        System.out.println(b1.name);
    }
}
