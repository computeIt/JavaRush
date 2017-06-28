package com.javarush.task.task39.task3906;

/**
 * Created by Hikioku on 27.06.2017.
 */
public interface Switchable {   //причем если назвать интерфейс каким-лтбо другим именем, то валидатор не примет решение :(
    boolean isOn();
    void turnOff();
    void turnOn();
}
