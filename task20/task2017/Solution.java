package com.javarush.task.task20.task2017;

import java.io.ObjectInputStream;
import java.io.Serializable;

//      На вход подается поток, в который записан сериализованный объект класса A либо класса B.
//        Десериализуй объект в методе getOriginalObject так, чтобы в случае возникновения
//                                                          исключения было выведено сообщение на экран и возвращен null.
//        Реализуй интерфейс Serializable где необходимо.

public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {
        A a;
        try {
            a = (A)objectStream.readObject();     //мы уверены, что получим объект А(т.к. В - тоже А)
        }catch(Exception e){                      //Exception e здесь подразумевается IOException или ClassNotFoundException
            System.out.println("error of deserialization");
            return null;
        }
        return a;
    }

    public class A implements Serializable{   //объявляем А serializable, потому что В, будучи его наследником, тоже станет serializable
        public A() {}
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {
    }
}
