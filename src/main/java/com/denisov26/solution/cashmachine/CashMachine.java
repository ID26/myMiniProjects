package com.denisov26.solution.cashmachine;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            com.javarush.task.task26.task2613.Operation operation;
            do {
                operation = com.javarush.task.task26.task2613.ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != com.javarush.task.task26.task2613.Operation.EXIT);
        } catch (InterruptOperationException ignored) {
            com.javarush.task.task26.task2613.ConsoleHelper.writeMessage("Terminated. Thank you for visiting JavaRush cash machine. Good luck.");
        }
    }
}
