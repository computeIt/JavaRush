package com.javarush.task.task38.task3809;

import java.lang.reflect.Field;

public final class ReflectionAnnotationUtil {
    public static void check(Object someObject) throws IllegalAccessException {
        Class<?> testedClass = someObject.getClass();//берем класс у тестируемого объекта
        for (Field field : testedClass.getDeclaredFields()) {//пробегаем по всем его полям
            if (field.isAnnotationPresent(LongPositive.class)) {//если какое-то поле отмечено аннотацией LongPositive
                processLongPositiveAnnotationField(someObject, testedClass, field);//передаем объект, класс и это поле в метод для дальнейшего обследования 
            }
        }
    }

    private static void processLongPositiveAnnotationField(Object someObject, Class<?> testedClass, Field field) throws
            IllegalAccessException
    {
        field.setAccessible(true);
        Class<?> fieldType = field.getType();

        //assert type is long
        if (!fieldType.equals(long.class)) {
            String msg = String.format("Поле %s в классе %s имеет аннотацию LongPositive, но его тип %s.",
                    field.getName(), testedClass.getSimpleName(), fieldType.getSimpleName());
            System.out.println(msg);
            return;
        }

        //assert value is positive
        long value = (long) field.get(someObject);
        if (value <= 0) {
            String msg = String.format("Поле %s в классе %s имеет аннотацию LongPositive, но его значение не положительное.",
                    field.getName(), testedClass.getSimpleName());
            System.out.println(msg);
        }
    }
}
