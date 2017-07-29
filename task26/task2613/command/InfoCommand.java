package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;


/**
 * Created by Андрей on 03.07.2017.
 */
class InfoCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException{
        boolean money = false;

        for (CurrencyManipulator currencyManipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators())
            if (currencyManipulator.hasMoney()) {
                if (currencyManipulator.getTotalAmount() > 0) {
                    ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
                    money = true;
                }
            }

        if (!money)
            ConsoleHelper.writeMessage("No money available.");
    }
}
