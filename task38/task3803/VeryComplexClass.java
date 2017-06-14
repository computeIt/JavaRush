package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
Напиши реализацию метода methodThrowsClassCastException(). Он должен всегда кидать Runtime исключение ClassCastException.

Напиши реализацию метода methodThrowsNullPointerException(). Он должен всегда кидать Runtime исключение NullPointerException.

Кинуть исключение (throw) явно нельзя.

Требования:
1. Метод methodThrowsClassCastException класса veryComplexClass не должен использовать ключевое слово throw.
2. Метод methodThrowsNullPointerException класса veryComplexClass не должен использовать ключевое слово throw.
3. Метод methodThrowsClassCastException класса veryComplexClass должен бросать исключение ClassCastException.
4. Метод methodThrowsNullPointerException класса veryComplexClass должен бросать исключение NullPointerException.*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object obj = 1;
        String[] array = (String[])obj;//некоторые вещи невозможно кастировать в некоторые другие вещи))
    }

    public void methodThrowsNullPointerException() {
        int[] array = null;
        int y = array.length;//любая попытка вызвать метод на null объекте влечет за собой NPE
    }

    public static void main(String[] args) {
        VeryComplexClass vcc = new VeryComplexClass();
        vcc.methodThrowsClassCastException();
        vcc.methodThrowsNullPointerException();

    }
}
