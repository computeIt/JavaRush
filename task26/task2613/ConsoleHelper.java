package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Андрей on 28.06.2017.
 */
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static Operation askOperation() throws InterruptOperationException {
        while(true) {
            try {
                System.out.println("choose an operation \n 1 - INFO \n 2 - DEPOSIT \n 3 - WITHDRAW \n 4 - EXIT");
                Integer choice = Integer.parseInt(bis.readLine());
                return Operation.getAllowableOperationByOrdinal(choice);
            } catch (Exception e) {
                System.out.println("wrong data. try again");
                //continue;
            }
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        System.out.println("input currency code");
        String result = "";
        while(true){
            try {
                result = bis.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(result.length() == 3)
                break;
            else{
                System.out.println("wrong data. try again");
            }
        }
        result = result.toUpperCase();
        return result;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        System.out.println("input 2 positive numbers - denomination + amount");
        String input = "";
        String[] inputArray;
        int denomination, amount = 0;

        while(true){
            try{
                input = bis.readLine();
                inputArray = input.split(" ");
                if(inputArray.length != 2)
                    throw new IllegalArgumentException();
                denomination = Integer.parseInt(inputArray[0]);
                amount = Integer.parseInt(inputArray[1]);
            }catch (Exception iae){
                System.out.println("wrong data. try again");
                continue;
            }
            break;
        }
        return inputArray;

    }

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String message = null;
        try {
            message = bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(message.equalsIgnoreCase("EXIT"))
            throw new InterruptOperationException();
        return message;
    }
}
