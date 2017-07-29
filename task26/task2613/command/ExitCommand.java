package com.javarush.task.task26.task2613.command;


import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

/**
 * Created by Андрей on 03.07.2017.
 */
class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("do you really want to exit? - <y, n>");
        if(ConsoleHelper.readString().equalsIgnoreCase("y"))
            ConsoleHelper.writeMessage("Bye");
    }
}
