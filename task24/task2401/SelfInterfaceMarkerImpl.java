package com.javarush.task.task24.task2401;

/**
 * Created by Addy on 23.10.2017.
 */
public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker {

    public void printClass(){
        System.out.println("your class is " + this.getClass().getSimpleName());
    }

    public String className(){
        return this.getClass().getSimpleName().toString();
    }
}
