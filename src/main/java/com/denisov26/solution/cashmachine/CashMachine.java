package com.denisov26.solution.cashmachine;

import com.denisov26.solution.cashmachine.command.CommandExecutor;
import com.denisov26.solution.cashmachine.command.LoginCommand;
import com.denisov26.solution.cashmachine.exception.InterruptOperationException;


import java.util.Locale;

public class CashMachine {
    public static String RESOURCE_PATH = ""/*CashMachine.class.getPackage().getName() + ".resources."*/;

    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation operation = Operation.LOGIN;
            CommandExecutor.execute(operation);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException ignored) {
            ConsoleHelper.printExitMessage();
        }
    }
}
