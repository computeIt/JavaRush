package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
Создай класс — фабрику исключений, который содержит один статический метод, возвращающий нужное исключение.
Этот метод должен принимать один параметр — энум.
Если передан энум:
* ExceptionApplicationMessage, верни исключение Exception
* ExceptionDBMessage, верни исключение RuntimeException
* ExceptionUserMessage, верни Error
* иначе верните IllegalArgumentException без сообщения.

В качестве сообщения (message) для каждого возвращаемого объекта используйте элементы энума,
в которых все знаки подчеркивания замените на пробелы. Сообщение должно быть в нижнем регистре за исключением первого символа.
Например, сообщение для ExceptionApplicationMessage.SOCKET_IS_CLOSED — «Socket is closed».

Верните класс созданной фабрики в методе Solution.getFactoryClass.

Энумы не меняй.

Требования:
1. Метод getFactoryClass должен возвращать фабрику исключений.
2. ExceptionApplicationMessage не должен содержать никакие дополнительные методов или конструкторы.
3. ExceptionDBMessage не должен содержать никакие дополнительные методов или конструкторы.
4. ExceptionUserMessage не должен содержать никакие дополнительные методов или конструкторы.
5. Статический метод фабрики исключений должен возвращать исключения, перечисленные в условии (включая сообщение)
для любых входящих параметров.
6. Фабрика должна иметь один метод и он должен быть статическим.*/

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        String s1 = ExceptionApplicationMessage.SOCKET_IS_CLOSED.toString();
        long t2 = System.currentTimeMillis();
        String s2 = ExceptionApplicationMessage.SOCKET_IS_CLOSED.name();
        long t3 = System.currentTimeMillis();
        System.out.println("time of using toString() -> " + (t2-t1));
        System.out.println("time of using name() -> " + (t3-t2));

        //разница между использованием метода name() и toString() для enum неочевидна
        // засечка времени показывает незначительную разницу(у меня получилось соответственно 0 и 3мс),
        // что неудивительно, если взглянуть на default реализацию toString() для enum
        //        public String toString() {
        //            return name;
        //        }
        //форумы и javadoc глаголят, что единственная разница в том, что toString() можно перегрузить(Override) 
        // и заставить возвращать что угодно, а name() в данной ситуации - непоколебимый столб 
        
    }
}
