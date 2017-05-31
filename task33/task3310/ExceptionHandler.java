package com.javarush.task.task33.task3310;

import static com.javarush.task.task33.task3310.Helper.printMessage;

/**
 * Created by Igor on 29.05.2017.
 */
public class ExceptionHandler {

    public static void log(Exception e){
        printMessage(e.toString());
    }
}
