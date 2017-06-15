package com.javarush.task.task38.task3804;

/**
 * Created by Igor on 15.06.2017.
 */
public class ExceptionFactory {

    public static Throwable getException(Enum param){

        if(param == null)
            return new IllegalArgumentException();

        String message = (param.toString().charAt(0) + param.toString().substring(1).toLowerCase()).replace("_", " ");

        if(param instanceof ExceptionApplicationMessage){
            return new Exception(message);
        }

        if(param instanceof ExceptionDBMessage){
            return new RuntimeException(message);
        }

        if(param instanceof ExceptionUserMessage)
            return new Error(message);

        else
            return new IllegalArgumentException();
    }
}
