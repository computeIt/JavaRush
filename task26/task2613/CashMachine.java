package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.io.IOException;
import java.util.Locale;


/**
 * Created by Андрей on 28.06.2017.
 */
public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        try{
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);

        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("ПРЕРВАННАЯ ОПЕРАЦИЯ!");
        }


//фабрика на то и фабрика - она внутри себя "родила" манипулятор на основе валюты(или же вернула уже существующий
// и добавила в него вложенные бабки
    }
}
