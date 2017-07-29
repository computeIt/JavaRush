package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.text.ParseException;

import static com.javarush.task.task26.task2613.ConsoleHelper.askCurrencyCode;

/**
 * Created by Андрей on 03.07.2017.
 */
class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException{
        String currencyCode = "";
        int summa;

        currencyCode = askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        ConsoleHelper.writeMessage("enter a summ");

        while(true){
            try{
                summa = Integer.parseInt(ConsoleHelper.readString());
                if(!currencyManipulator.isAmountAvailable(summa))
                    throw new IllegalArgumentException();
                break;
            }catch (IllegalArgumentException e){
                ConsoleHelper.writeMessage("wrong data. try again");
            }
        }



    }
}
