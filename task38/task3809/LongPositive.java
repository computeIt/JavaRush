package com.javarush.task.task38.task3809;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)//вот оно - то самое исправление, которое требуется для работы программы
@Target(value = ElementType.FIELD)
public @interface LongPositive {
}
