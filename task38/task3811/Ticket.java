package com.javarush.task.task38.task3811;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Андрей on 15.06.2017.
 */

@Retention(RetentionPolicy.RUNTIME)//Аннотация Ticket должна быть доступна во время выполнения программы.
@Target(ElementType.TYPE)
public @interface Ticket {
    enum Priority{                  //Аннотация Ticket должна содержать enum Priority c элементами LOW, MEDIUM, HIGH.
        LOW,
        MEDIUM,
        HIGH
    }
    Priority priority() default Priority.MEDIUM;    //Аннотация Ticket должна содержать свойство priority - по умолчанию Priority.MEDIUM.
    String[] tags() default {};             //Аннотация Ticket должна содержать свойство tags - массив строк, пустой по умолчанию.
    String createdBy() default "Amigo";     //Аннотация Ticket должна содержать свойство createdBy - строку, равную "Amigo" по умолчанию.
}

//Аннотация Ticket должна применяться только к новым типам данных - вот это я не понял :(
