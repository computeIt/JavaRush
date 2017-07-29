package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.IOException;

import static com.javarush.task.task26.task2613.ConsoleHelper.askCurrencyCode;
import static com.javarush.task.task26.task2613.ConsoleHelper.getValidTwoDigits;

/**
 * Created by Андрей on 03.07.2017.
 */
class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = "";
        String[] data = new String[2];
        try {
            currencyCode = askCurrencyCode();
            data = getValidTwoDigits(currencyCode);}
        catch (NumberFormatException  i){
            ConsoleHelper.writeMessage("errrrrror!!!");
        }
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode).addAmount(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
    }
}
