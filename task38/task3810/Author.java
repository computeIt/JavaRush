package com.javarush.task.task38.task3810;

public @interface Author {
    String value();//глюк!!!! сюда просится default "";
    Position position() default Position.OTHER;
}
